package alexandre.com.br.movies.presentation.mvp.view;

import java.util.List;

import alexandre.com.br.movies.presentation.mvp.model.MovieModel;

public interface MovieListView extends LoadDataView {

    void renderMovies(List<MovieModel> movies);
    void clearMovies();

}
