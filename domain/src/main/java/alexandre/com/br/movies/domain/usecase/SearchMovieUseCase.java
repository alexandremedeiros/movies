package alexandre.com.br.movies.domain.usecase;


import java.util.List;


import alexandre.com.br.movies.domain.BaseUseCase;
import alexandre.com.br.movies.domain.BaseUseCaseCallback;
import alexandre.com.br.movies.domain.entity.Movie;
import alexandre.com.br.movies.domain.service.API;
import alexandre.com.br.movies.domain.service.SearchMovieResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchMovieUseCase extends BaseUseCase {

    public interface SearchMovieUseCaseCallback extends BaseUseCaseCallback {
        void onMoviesSearched(List<Movie> movieEntities);
    }

    private String apiKey;
    private String query;

    public SearchMovieUseCase(String apiKey, String query, SearchMovieUseCaseCallback callback) {
        super(callback);
        this.apiKey = apiKey;
        this.query = query;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().search(apiKey, query, new Callback<SearchMovieResponse>() {
            @Override
            public void success(SearchMovieResponse searchMovieResponse, Response response) {
                ((SearchMovieUseCaseCallback) callback).onMoviesSearched(searchMovieResponse.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind() == RetrofitError.Kind.NETWORK) {
                    errorReason = INTERNET_ERROR;
                } else {
                    errorReason = error.getResponse().getReason();
                }
                onCancel();
            }
        });
    }
}
