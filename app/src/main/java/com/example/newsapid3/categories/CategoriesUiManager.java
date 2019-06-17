package com.example.newsapid3.categories;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.newsapid3.R;
import com.example.newsapid3.di.ScreenScope;
import com.example.newsapid3.lifecycle.ScreenLifeCycleTask;
import com.example.newsapid3.utils.ButterKnifeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@ScreenScope
public class CategoriesUiManager extends ScreenLifeCycleTask {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;

    @Inject
    CategoriesUiManager(){}

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle("News Categories");
    }

    @Override
    public void onExitScope() {
        ButterKnifeUtils.unbind(unbinder);
    }
}
