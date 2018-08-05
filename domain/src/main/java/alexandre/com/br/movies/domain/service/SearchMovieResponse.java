package alexandre.com.br.movies.domain.service;

import java.util.List;

import alexandre.com.br.movies.domain.entity.Movie;


public class SearchMovieResponse {
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
