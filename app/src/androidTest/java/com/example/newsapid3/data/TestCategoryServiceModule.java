package com.example.newsapid3.data;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@Module
public abstract class TestCategoryServiceModule {

    @Binds
    abstract CategoryService bindCategoryService(TestCategoryService categoryService);

    @Provides
    @Named("network_scheduler")
    static Scheduler provideNetworkScheduler(){ return Schedulers.trampoline();}
}
