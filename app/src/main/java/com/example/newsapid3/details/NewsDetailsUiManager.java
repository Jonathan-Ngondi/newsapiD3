package com.example.newsapid3.details;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.newsapid3.R;
import com.example.newsapid3.lifecycle.ScreenLifeCycleTask;
import com.example.newsapid3.ui.ScreenNavigator;
import com.example.newsapid3.utils.ButterKnifeUtils;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
public class NewsDetailsUiManager extends ScreenLifeCycleTask {

    private final String categoryName;

    private final ScreenNavigator screenNavigator;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;

    @Inject
    NewsDetailsUiManager(@Named("category_name") String categoryName, ScreenNavigator screenNavigator){
        this.categoryName = categoryName;
        this.screenNavigator = screenNavigator;
    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(categoryName);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> screenNavigator.pop());
    }

    @Override
    public void onExitScope() {
        toolbar.setNavigationOnClickListener(null);
        ButterKnifeUtils.unbind(unbinder);
    }
}
