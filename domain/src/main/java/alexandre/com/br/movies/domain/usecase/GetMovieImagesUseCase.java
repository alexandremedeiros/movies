package alexandre.com.br.movies.domain.usecase;

import java.util.List;


import alexandre.com.br.movies.domain.BaseUseCase;
import alexandre.com.br.movies.domain.BaseUseCaseCallback;
import alexandre.com.br.movies.domain.entity.Image;
import alexandre.com.br.movies.domain.service.API;
import alexandre.com.br.movies.domain.service.GetMovieImagesResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetMovieImagesUseCase extends BaseUseCase {

    public interface GetMovieImagesUseCaseCallback extends BaseUseCaseCallback {
        void onImagesUrlsLoaded(List<Image> backdrops, List<Image> posters);
    }

    private String apiKey;
    private int movieID;

    public GetMovieImagesUseCase(String apiKey, int movieID, GetMovieImagesUseCaseCallback callback) {
        super(callback);
        this.movieID = movieID;
        this.apiKey = apiKey;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().movieImages(apiKey, movieID, new Callback<GetMovieImagesResponse>() {
            @Override
            public void success(GetMovieImagesResponse getMovieImagesResponse, Response response) {
                ((GetMovieImagesUseCaseCallback)callback).onImagesUrlsLoaded(getMovieImagesResponse.getBackdrops(), getMovieImagesResponse.getPosters());
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
