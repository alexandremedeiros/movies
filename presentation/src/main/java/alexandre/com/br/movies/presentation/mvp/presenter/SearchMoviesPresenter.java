package alexandre.com.br.movies.presentation.mvp.presenter;


import android.text.TextUtils;

import java.util.List;

import alexandre.com.br.movies.BuildConfig;
import alexandre.com.br.movies.domain.entity.Configuration;
import alexandre.com.br.movies.domain.entity.Movie;
import alexandre.com.br.movies.domain.usecase.GetImageConfigurationUseCase;
import alexandre.com.br.movies.domain.usecase.SearchMovieUseCase;
import alexandre.com.br.movies.presentation.MoviesApp;
import alexandre.com.br.movies.presentation.mapper.MovieMapper;
import alexandre.com.br.movies.presentation.mvp.BasePresenter;
import alexandre.com.br.movies.presentation.mvp.view.SearchMoviesView;

public class SearchMoviesPresenter implements BasePresenter {

    private SearchMoviesView view;
    private String apiKey;
    private String lastQuery = "";

    public SearchMoviesPresenter(SearchMoviesView view) {
        this.view = view;
        this.apiKey = BuildConfig.TMDB_API_KEY;
    }

    @Override
    public void createView() {
        hideAllViews();
        view.showLoading();

        checkIfHasTheBaseImageURL();
    }

    @Override
    public void destroyView() {
        view.cleanTimer();
    }

    public void performSearch(String query) {
        if(!TextUtils.isEmpty(query) && !lastQuery.equals(query.trim())) { // avoid blank searches and consecutive repeated searches

            lastQuery = query.trim(); // store the last query

            view.hideView();
            view.showLoading();

            MoviesApp.JOB_MANAGER.addJobInBackground(new SearchMovieUseCase(apiKey, lastQuery, new SearchMovieUseCase.SearchMovieUseCaseCallback() {
                @Override
                public void onMoviesSearched(List<Movie> movieEntities) {
                    view.hideLoading();
                    view.renderMoviesList(new MovieMapper().toModels(movieEntities));
                    view.showView();
                }

                @Override
                public void onError(String reason) {
                    view.hideLoading();
                    view.removeMoviesList();
                    view.showFeedback(reason);
                    lastQuery = "";
                }
            }));
        }
    }

    private void hideAllViews() {
        view.hideView();
        view.hideLoading();
    }

    private void checkIfHasTheBaseImageURL() {
        if(!MoviesApp.LOCAL_DATA.hasBaseImageURL()) {
            MoviesApp.JOB_MANAGER.addJobInBackground(new GetImageConfigurationUseCase(apiKey, new GetImageConfigurationUseCase.GetImageConfigurationUseCaseCallback() {
                @Override
                public void onConfigurationDownloaded(Configuration configurationEntity) {
                    MoviesApp.LOCAL_DATA.storeBaseImageURL(configurationEntity.getBase_url());

                    showEmptyMovies();
                }

                @Override
                public void onError(String reason) {
                    view.hideLoading();
                    view.showView();
                    view.showFeedback(reason);
                }
            }));

        } else {
            showEmptyMovies();
        }
    }

    private void showEmptyMovies() {
        view.hideLoading();
        view.removeMoviesList();
        view.showView();
    }
}
