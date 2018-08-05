package alexandre.com.br.movies.domain.service;


import alexandre.com.br.movies.domain.entity.MovieDetail;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class API {

    public static final String BASE_URL = "http://api.themoviedb.org/3";

    public interface ROUTES {

        @GET("/configuration")
        void configurations(@Query("api_key") String apiKey, Callback<GetImageConfigurationResponse> callback);


        @GET("/search/movie")
        void search(@Query("api_key") String apiKey, @Query("query") String query, Callback<SearchMovieResponse> callback);


        @GET("/movie/{id}")
        void movieDetails(@Query("api_key") String apiKey, @Path("id") int movieID, Callback<MovieDetail> callback);


        @GET("/movie/{id}/images")
        void movieImages(@Query("api_key") String apiKey, @Path("id") int movieID, Callback<GetMovieImagesResponse> callback);
    }

    public static ROUTES http() {
        return new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(ROUTES.class);
    }

}
