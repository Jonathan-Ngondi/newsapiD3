package com.example.newsapid3.data;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@Module
public abstract class CategoryServiceModule {

    @Provides
    @Singleton
    static CategoryService provideCategoryService(Retrofit retrofit){
        return retrofit.create(CategoryService.class);
    }

    @Provides
    @Named("network_scheduler")
    static Scheduler provideNetworkScheduler(){
        return Schedulers.io();
    }
}
