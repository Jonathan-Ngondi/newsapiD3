package com.example.newsapid3.categories;

import com.example.newsapid3.base.ScreenModule;
import com.example.newsapid3.di.ScreenComponent;
import com.example.newsapid3.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        CategoriesScreenModule.class,
})
public interface CategoriesComponent extends ScreenComponent<CategoriesController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CategoriesController>{

    }
}
