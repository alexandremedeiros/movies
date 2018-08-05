package alexandre.com.br.movies.domain.usecase;


import alexandre.com.br.movies.domain.BaseUseCase;
import alexandre.com.br.movies.domain.BaseUseCaseCallback;
import alexandre.com.br.movies.domain.entity.MovieDetail;
import alexandre.com.br.movies.domain.service.API;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetMovieDetailUseCase extends BaseUseCase {

    public interface GetMovieDetailUseCaseCallback extends BaseUseCaseCallback {
        void onMovieDetailLoaded(MovieDetail movieDetailEntity);
    }

    private int movieID;
    private String apiKey;

    public GetMovieDetailUseCase(String apiKey, int movieID, GetMovieDetailUseCaseCallback callback) {
        super(callback);
        this.movieID = movieID;
        this.apiKey = apiKey;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().movieDetails(apiKey, movieID, new Callback<MovieDetail>() {
            @Override
            public void success(MovieDetail movieDetailEntity, Response response) {
                ((GetMovieDetailUseCaseCallback)callback).onMovieDetailLoaded(movieDetailEntity);
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
