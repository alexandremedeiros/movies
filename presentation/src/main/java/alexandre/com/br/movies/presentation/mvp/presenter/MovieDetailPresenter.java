package alexandre.com.br.movies.presentation.mvp.presenter;

import android.text.TextUtils;

import alexandre.com.br.movies.BuildConfig;
import alexandre.com.br.movies.domain.entity.MovieDetail;
import alexandre.com.br.movies.domain.usecase.GetMovieDetailUseCase;
import alexandre.com.br.movies.presentation.MoviesApp;
import alexandre.com.br.movies.presentation.mapper.MovieMapper;
import alexandre.com.br.movies.presentation.mvp.BasePresenter;
import alexandre.com.br.movies.presentation.mvp.model.MovieModel;
import alexandre.com.br.movies.presentation.mvp.view.MovieDetailView;

public class MovieDetailPresenter implements BasePresenter {

    private MovieDetailView view;
    private MovieModel movieModel;
    private String apiKey;

    public MovieDetailPresenter(MovieDetailView view, MovieModel movieModel) {
        this.view = view;
        this.movieModel = movieModel;
        this.apiKey = BuildConfig.TMDB_API_KEY;
    }

    @Override
    public void createView() {
        hideAllViews();
        view.showLoading();

        downloadMovieDetails();
    }

    @Override
    public void destroyView() {}

    public void onHomepageClicked() {
        if(!TextUtils.isEmpty(movieModel.getHomepage())) {
            view.openMovieWebsite(movieModel.getHomepage());
        }
    }


    public void onMainViewScrolled() {
        view.updateToolbarColor();
    }

    private void hideAllViews() {
        view.hideView();
        view.hideLoading();
        view.hideRetry();
        view.hideEmpty();
    }

    private void downloadMovieDetails() {
        MoviesApp.JOB_MANAGER.addJobInBackground(new GetMovieDetailUseCase(apiKey, movieModel.getId(), new GetMovieDetailUseCase.GetMovieDetailUseCaseCallback() {
            @Override
            public void onMovieDetailLoaded(MovieDetail movieDetailEntity) {
                updateMovieModel(movieDetailEntity);

                view.updateBackground(movieModel.getBigCover());
                view.updateTitle(movieModel.getName());

                if (TextUtils.isEmpty(movieModel.getYearOfRelease())) {
                    view.hideYearOfRelease();
                } else {
                    view.updateYearOfRelease(movieModel.getYearOfRelease());
                }

                if (TextUtils.isEmpty(movieModel.getHomepage())) {
                    view.hideHomepage();
                } else {
                    view.updateHomepage(movieModel.getHomepage());
                }

                if (movieModel.getCompanies().isEmpty()) {
                    view.hideCompanies();
                } else {
                    view.updateCompanies(movieModel.getCompanies());
                }

                if (TextUtils.isEmpty(movieModel.getTagline())) {
                    view.hideTagline();
                } else {
                    view.updateTagline(movieModel.getTagline());
                }

                if (TextUtils.isEmpty(movieModel.getOverview())) {
                    view.hideOverview();
                } else {
                    view.updateOverview(movieModel.getOverview());
                }

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

    private void updateMovieModel(MovieDetail detailEntity) {
        movieModel = new MovieMapper().addDetails(movieModel, detailEntity);
    }
}
