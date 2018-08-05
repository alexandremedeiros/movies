package alexandre.com.br.movies.presentation.mapper;


import alexandre.com.br.movies.domain.BaseMapper;
import alexandre.com.br.movies.domain.entity.Image;
import alexandre.com.br.movies.presentation.Utils;
import alexandre.com.br.movies.presentation.mvp.model.ImageModel;

public class ImageMapper extends BaseMapper<Image, ImageModel> {

    private String size;

    public ImageMapper(String size) {
        this.size = size;
    }

    @Override
    public ImageModel toModel(Image entity) {
        ImageModel model = new ImageModel();

        model.setOriginalURL(Utils.buildCompleteImageURL(entity.getFile_path(), "original"));
        model.setUrl(Utils.buildCompleteImageURL(entity.getFile_path(), size));

        return model;
    }

    @Override
    public ImageModel deserializeModel(String serializedModel) {
        return gson.fromJson(serializedModel, ImageModel.class);
    }
}
