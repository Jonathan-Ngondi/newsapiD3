package com.example.newsapid3.details;

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
 * @date 14/06/2019
 */
@Module
public abstract class NewsDetailsScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifeCycleTask bindsUiManagerTask(NewsDetailsUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Article")
    abstract ItemRenderer<? extends RecyclerItem> bindNewsDetailRenderer(NewsDetailsRenderer renderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers){
        return new RecyclerDataSource(renderers);
    }
}
