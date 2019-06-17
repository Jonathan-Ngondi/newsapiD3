package com.example.newsapid3.base;

import com.example.newsapid3.di.ForScreen;
import com.example.newsapid3.di.ScreenScope;
import com.example.newsapid3.lifecycle.DisposableManager;
import com.example.newsapid3.lifecycle.ScreenLifeCycleTask;

import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@Module
public abstract class ScreenModule {
    @Provides
    @ScreenScope
    @ForScreen
    static DisposableManager providesDisposableManager(){ return new DisposableManager();}

    @Multibinds
    abstract Set<ScreenLifeCycleTask> screenLifeCycleTasks();
}
