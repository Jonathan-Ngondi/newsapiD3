package com.example.newsapid3.ui;

import com.example.newsapid3.lifecycle.ActivityLifeCycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator providesScreenNavigator(DefaultScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifeCycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);
}
