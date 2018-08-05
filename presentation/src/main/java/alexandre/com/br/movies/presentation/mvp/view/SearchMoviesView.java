package alexandre.com.br.movies.presentation.mvp.view;

import java.util.List;

import alexandre.com.br.movies.presentation.mvp.model.MovieModel;

public interface SearchMoviesView extends LoadDataView {

    void renderMoviesList(List<MovieModel> movies);
    void removeMoviesList();

    void cleanTimer();
}
