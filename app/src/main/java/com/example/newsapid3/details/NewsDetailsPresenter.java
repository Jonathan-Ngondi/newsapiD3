package com.example.newsapid3.details;

import com.example.newsapid3.data.CategoryRepository;
import com.example.newsapid3.di.ForScreen;
import com.example.newsapid3.di.ScreenScope;
import com.example.newsapid3.lifecycle.DisposableManager;
import com.example.newsapid3.ui.ScreenNavigator;
import com.example.poweradapter.adapter.RecyclerDataSource;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@ScreenScope
final class NewsDetailsPresenter {

    private final NewsDetailsViewModel viewModel;
    private final CategoryRepository articlesRepository;
    private final ScreenNavigator screenNavigator;
    private final DisposableManager disposableManager;
    private final RecyclerDataSource dataSource;
    private final String category;

    @Inject
    NewsDetailsPresenter(NewsDetailsViewModel viewModel,
                         CategoryRepository repository,
                         ScreenNavigator screenNavigator,
                         @ForScreen DisposableManager disposableManager,
                         RecyclerDataSource dataSource,
                         @Named("category_name") String category){

        this.viewModel = viewModel;
        this.articlesRepository = repository;
        this.screenNavigator = screenNavigator;
        this.disposableManager = disposableManager;
        this.dataSource = dataSource;
        this.category = category;
        loadArticles();
    }

    private void loadArticles(){
        disposableManager.add(
                articlesRepository.getNewsArticles(category)
                .doOnSubscribe( __ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d,t)-> viewModel.loadingUpdated().accept(false))
                .doOnSuccess(__ -> viewModel.articlesUpdated().run())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSource::setData, viewModel.onError()));

    }
}
