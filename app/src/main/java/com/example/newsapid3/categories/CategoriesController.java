package com.example.newsapid3.categories;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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
public class CategoriesController extends BaseController {

    @Inject CategoriesPresenter presenter;
    @Inject CategoriesViewModel viewModel;
    @Inject RecyclerDataSource dataSource;

    @BindView(R.id.category_list)
    RecyclerView categoryList;
    @BindView(R.id.loading_indicator)
    ProgressBar loadingView;
    @BindView(R.id.tv_error)
    TextView errorTextView;

    @Override
    protected void onViewBound(View view) {
        categoryList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        categoryList.setAdapter(new RecyclerAdapter(dataSource));
    }


    @Override
    protected int layoutRes() {
        return R.layout.screen_categories;
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.loading()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE: View.GONE);
                    categoryList.setVisibility(loading ? View.GONE: View.VISIBLE);
                    errorTextView.setVisibility(loading ? View.GONE: errorTextView.getVisibility());

                }),
                viewModel.error()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(errorRes ->{
                    if(errorRes == -1){
                        errorTextView.setText(null);
                        errorTextView.setVisibility(View.GONE);
                    } else {
                        errorTextView.setVisibility(View.VISIBLE);
                        categoryList.setVisibility(View.GONE);
                        errorTextView.setText(errorRes);
                    }
                })
        };
    }
}
