package alexandre.com.br.movies.presentation.mvp.presenter;

import java.util.List;


import alexandre.com.br.movies.BuildConfig;
import alexandre.com.br.movies.domain.entity.Image;
import alexandre.com.br.movies.domain.usecase.GetMovieImagesUseCase;
import alexandre.com.br.movies.presentation.MoviesApp;
import alexandre.com.br.movies.presentation.mapper.ImageMapper;
import alexandre.com.br.movies.presentation.mvp.BasePresenter;
import alexandre.com.br.movies.presentation.mvp.view.MovieImagesView;

public class MovieImagesPresenter implements BasePresenter {

    private MovieImagesView view;
    private int movieID;

    public MovieImagesPresenter(MovieImagesView view, int movieID) {
        this.movieID = movieID;
        this.view = view;
    }

    @Override
    public void createView() {
        hideAllViews();

        view.showLoading();

        downloadMovieImages();
    }

    @Override
    public void destroyView() {}

    private void hideAllViews() {
        view.hideView();
        view.hideEmpty();
        view.hideLoading();
        view.hideRetry();
    }

    private void downloadMovieImages() {
        MoviesApp.JOB_MANAGER.addJobInBackground(new GetMovieImagesUseCase(BuildConfig.TMDB_API_KEY, movieID, new GetMovieImagesUseCase.GetMovieImagesUseCaseCallback() {
            @Override
            public void onImagesUrlsLoaded(List<Image> backdrops, List<Image> posters) {
                view.renderTabs(new ImageMapper("w780").toModels(backdrops), new ImageMapper("w500").toModels(posters));
                view.hideLoading();
                view.showView();
            }

            @Override
            public void onError(String reason) {
                view.showFeedback(reason);
                view.destroyItself();
            }
        }));
    }
}
