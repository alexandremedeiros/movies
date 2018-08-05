package alexandre.com.br.movies.domain.usecase;


import alexandre.com.br.movies.domain.BaseUseCase;
import alexandre.com.br.movies.domain.BaseUseCaseCallback;
import alexandre.com.br.movies.domain.entity.Configuration;
import alexandre.com.br.movies.domain.service.API;
import alexandre.com.br.movies.domain.service.GetImageConfigurationResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetImageConfigurationUseCase extends BaseUseCase {

    public interface GetImageConfigurationUseCaseCallback extends BaseUseCaseCallback {
        void onConfigurationDownloaded(Configuration configurationEntity);
    }

    private String apiKey;

    public GetImageConfigurationUseCase(String apiKey, GetImageConfigurationUseCaseCallback callback) {
        super(callback);
        this.apiKey = apiKey;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().configurations(apiKey, new Callback<GetImageConfigurationResponse>() {
            @Override
            public void success(GetImageConfigurationResponse getImageConfigurationResponse, Response response) {
                ((GetImageConfigurationUseCaseCallback) callback).onConfigurationDownloaded(getImageConfigurationResponse.getImages());
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
