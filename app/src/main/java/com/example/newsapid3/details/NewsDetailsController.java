package com.example.newsapid3.details;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.example.newsapid3.R;
import com.example.newsapid3.base.BaseController;
import com.example.poweradapter.adapter.RecyclerAdapter;
import com.example.poweradapter.adapter.RecyclerDataSource;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class NewsDetailsController extends BaseController {

    static final String CATEGORY_NAME_KEY = "category_name";

    public static Controller newInstance(String category){
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_NAME_KEY, category);
        return new NewsDetailsController(bundle);
    }


    @Inject
    NewsDetailsPresenter presenter;
    @Inject NewsDetailsViewModel viewModel;
    @Inject RecyclerDataSource dataSource;

    @BindView(R.id.article_list)
    RecyclerView articleList;
    @BindView(R.id.loading_indicator_article)
    ProgressBar loadingView;
    @BindView(R.id.tv_article_error)
    TextView errorTextView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;


    @Override
    protected void onViewBound(View view) {
        articleList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        articleList.setAdapter(new RecyclerAdapter(dataSource));

    }

    public NewsDetailsController(Bundle bundle) {
        super(bundle);
    }



    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    articleList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorTextView.setVisibility(loading ? View.GONE : errorTextView.getVisibility());
                }),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {
                    if (errorRes == -1){
                        errorTextView.setText(null);
                        errorTextView.setVisibility(View.GONE);
                    } else {
                        errorTextView.setVisibility(View.VISIBLE);
                        articleList.setVisibility(View.GONE);
                        errorTextView.setText(errorRes);
                    }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_articles;
    }
}
