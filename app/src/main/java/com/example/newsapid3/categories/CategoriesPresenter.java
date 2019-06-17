package com.example.newsapid3.categories;

import com.example.newsapid3.data.CategoryRepository;
import com.example.newsapid3.di.ForScreen;
import com.example.newsapid3.di.ScreenScope;
import com.example.newsapid3.lifecycle.DisposableManager;
import com.example.newsapid3.ui.ScreenNavigator;
import com.example.poweradapter.adapter.RecyclerDataSource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@ScreenScope
final class CategoriesPresenter {

    private final CategoriesViewModel viewModel;
    private final ScreenNavigator screenNavigator;
    private final CategoryRepository repository;
    private final DisposableManager disposableManager;
    private final RecyclerDataSource dataSource;

    @Inject
    CategoriesPresenter(CategoriesViewModel viewModel,
                        ScreenNavigator screenNavigator,
                        CategoryRepository repository,
                        @ForScreen DisposableManager disposableManager,
                        RecyclerDataSource dataSource){

        this.viewModel = viewModel;
        this.screenNavigator = screenNavigator;
        this.repository = repository;
        this.disposableManager = disposableManager;
        this.dataSource = dataSource;

        loadCategories();
    }

    private void loadCategories() {

        disposableManager.add(repository.getCategories()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d,t) -> viewModel.loadingUpdated().accept(false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSource::setData, viewModel.onError()));
    }

    void onCategoryClicked(String category){
        screenNavigator.goToNewsCategories(category);

    }


}
