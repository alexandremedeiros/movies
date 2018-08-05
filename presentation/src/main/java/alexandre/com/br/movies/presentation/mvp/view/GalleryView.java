package alexandre.com.br.movies.presentation.mvp.view;

import java.util.List;

import alexandre.com.br.movies.presentation.mvp.model.ImageModel;

public interface GalleryView extends LoadDataView {
    void renderImages(List<ImageModel> images);
    void clearImages();
}
