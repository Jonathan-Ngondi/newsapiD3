package com.example.newsapid3.base;

import android.app.Activity;

import com.example.newsapid3.home.MainActivity;
import com.example.newsapid3.home.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@Module(subcomponents = {MainActivityComponent.class,})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> providesMainActivityInjector(MainActivityComponent.Builder builder);

}
