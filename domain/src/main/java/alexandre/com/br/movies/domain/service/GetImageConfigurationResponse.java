package alexandre.com.br.movies.domain.service;


import alexandre.com.br.movies.domain.entity.Configuration;

public class GetImageConfigurationResponse {

    private Configuration images;

    public Configuration getImages() {
        return images;
    }

    public void setImages(Configuration images) {
        this.images = images;
    }
}
