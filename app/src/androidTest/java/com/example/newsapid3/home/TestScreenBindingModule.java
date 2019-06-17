package com.example.newsapid3.home;

import com.bluelinelabs.conductor.Controller;
import com.example.newsapid3.categories.CategoriesComponent;
import com.example.newsapid3.categories.CategoriesController;
import com.example.newsapid3.details.NewsDetailsComponent;
import com.example.newsapid3.details.NewsDetailsController;
import com.example.newsapid3.di.ControllerKey;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@Module(subcomponents = {
        CategoriesComponent.class,
        NewsDetailsComponent.class,
})
public abstract class TestScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(CategoriesController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindcategoryControllerInjector(CategoriesComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(NewsDetailsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindNewsDetailsControllerInjector(
            NewsDetailsComponent.Builder builder);
}