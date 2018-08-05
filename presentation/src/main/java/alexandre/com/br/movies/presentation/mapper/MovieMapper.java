package alexandre.com.br.movies.presentation.mapper;


import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import alexandre.com.br.movies.domain.BaseMapper;
import alexandre.com.br.movies.domain.entity.Company;
import alexandre.com.br.movies.domain.entity.Movie;
import alexandre.com.br.movies.domain.entity.MovieDetail;
import alexandre.com.br.movies.presentation.Utils;
import alexandre.com.br.movies.presentation.mvp.model.MovieModel;

public class MovieMapper extends BaseMapper<Movie, MovieModel> {

    @Override
    public MovieModel toModel(Movie entity) {
        MovieModel movieModel = new MovieModel();

        movieModel.setId(entity.getId());
        movieModel.setName(entity.getTitle());
        movieModel.setYearOfRelease(Utils.getYearFromServerDate(entity.getRelease_date()));

        if(!TextUtils.isEmpty(entity.getPoster_path())) {
            movieModel.setSmallCover(Utils.buildCompleteImageURL(entity.getPoster_path(), "w154"));
            movieModel.setBigCover(Utils.buildCompleteImageURL(entity.getPoster_path(), "original"));
        }

        return movieModel;
    }

    @Override
    public MovieModel deserializeModel(String serializedModel) {
        return gson.fromJson(serializedModel, MovieModel.class);
    }

    public MovieModel addDetails(MovieModel receiver, MovieDetail detailsToBeInserted) {

        receiver.setHomepage(detailsToBeInserted.getHomepage());

        List<String> companies = new ArrayList<>();
        for(Company company : detailsToBeInserted.getProduction_companies()) {
            companies.add(company.getName());
        }
        //remove the first and the last character
        receiver.setCompanies(companies.toString().substring(1,companies.toString().length()-1));

        receiver.setTagline(detailsToBeInserted.getTagline());
        receiver.setOverview(detailsToBeInserted.getOverview());

        return receiver;
    }
}
