package alexandre.com.br.movies.presentation.mvp.presenter;


import alexandre.com.br.movies.presentation.mvp.BasePresenter;
import alexandre.com.br.movies.presentation.mvp.view.LoadingImageView;

public class LoadingImagePresenter implements BasePresenter {

    private LoadingImageView view;
    private String url;

    public LoadingImagePresenter(LoadingImageView view, String url) {
        this.view = view;
        this.url = url;
    }

    @Override
    public void createView() {
        hideAllViews();
        view.showLoading();

        view.renderImage(url);
    }

    @Override
    public void destroyView() {

    }

    private void hideAllViews() {
        view.hideView();
        view.hideEmpty();
        view.hideLoading();
        view.hideRetry();
    }

    public void onImageRendered() {
        view.hideLoading();
        view.showView();
    }

    public void onRenderingError() {
        view.hideLoading();
        view.showRetry("An error occurred.");
    }
}
