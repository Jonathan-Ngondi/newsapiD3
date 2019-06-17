package com.example.newsapid3.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@Module
public class ApplicationModule {

    private final Application application;

    ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    Context provideApplicationContext(){
        return application;
    }
}
