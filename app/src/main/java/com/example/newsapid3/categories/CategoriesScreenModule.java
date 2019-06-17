package com.example.newsapid3.categories;

import com.example.newsapid3.di.ScreenScope;
import com.example.newsapid3.lifecycle.ScreenLifeCycleTask;
import com.example.poweradapter.adapter.RecyclerDataSource;
import com.example.poweradapter.item.ItemRenderer;
import com.example.poweradapter.item.RecyclerItem;
import com.example.poweradapter.item.RenderKey;

import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@Module
public abstract class CategoriesScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifeCycleTask bindUIManager(CategoriesUiManager categoriesUiManager);

    @Binds
    @IntoMap
    @RenderKey("Category")
    abstract ItemRenderer<? extends RecyclerItem> bindCategoryRenderer(CategoryRenderer categoryRenderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideRecyclerDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> rendererMap){
        return new RecyclerDataSource(rendererMap);
    }

}
