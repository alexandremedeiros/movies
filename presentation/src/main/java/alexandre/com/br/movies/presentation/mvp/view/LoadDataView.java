package alexandre.com.br.movies.presentation.mvp.view;

import alexandre.com.br.movies.presentation.mvp.BaseView;

public interface LoadDataView extends BaseView {

    void showLoading();
    void hideLoading();

    void showRetry(String msg);
    void hideRetry();

    void showEmpty(String msg);
    void hideEmpty();
}
