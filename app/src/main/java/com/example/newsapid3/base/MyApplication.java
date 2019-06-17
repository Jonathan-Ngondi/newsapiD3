package com.example.newsapid3.base;

import android.app.Application;

import com.example.newsapid3.BuildConfig;
import com.example.newsapid3.di.ActivityInjector;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class MyApplication extends Application {


    @Inject ActivityInjector activityInjector;

    protected ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = initComponent();
        component.inject(this);

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected ApplicationComponent initComponent(){
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
