package alexandre.com.br.movies.presentation.gui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import alexandre.com.br.movies.R;
import alexandre.com.br.movies.presentation.gui.BaseActivity;
import alexandre.com.br.movies.presentation.gui.fragment.SearchMoviesFragment;

public class SearchMoviesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_with_toolbar;
    }

    @Override
    public Fragment getMainFragment() {
        return SearchMoviesFragment.newInstance();
    }
}
