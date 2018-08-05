package alexandre.com.br.movies.domain.service;

import java.util.List;

import alexandre.com.br.movies.domain.entity.Image;


public class GetMovieImagesResponse {
    private List<Image> backdrops;
    private List<Image> posters;

    public List<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Image> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Image> getPosters() {
        return posters;
    }

    public void setPosters(List<Image> posters) {
        this.posters = posters;
    }
}
