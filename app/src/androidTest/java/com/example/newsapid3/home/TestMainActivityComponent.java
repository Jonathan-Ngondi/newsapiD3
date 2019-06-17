package com.example.newsapid3.home;

import com.example.newsapid3.di.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@ActivityScope
@Subcomponent(modules = {TestScreenBindingModule.class,})
public interface TestMainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }

}