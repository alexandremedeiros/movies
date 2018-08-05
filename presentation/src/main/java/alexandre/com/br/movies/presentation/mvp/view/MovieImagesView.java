package alexandre.com.br.movies.presentation.mvp.view;

import java.util.List;

import alexandre.com.br.movies.presentation.mvp.model.ImageModel;

public interface MovieImagesView extends LoadDataView {
    void renderTabs(List<ImageModel> backdrops, List<ImageModel> posters);
}
