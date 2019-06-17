package com.example.newsapid3.di;

import com.example.newsapid3.lifecycle.DisposableManager;

import dagger.android.AndroidInjector;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public interface ScreenComponent<T> extends AndroidInjector<T> {

    @ForScreen
    DisposableManager disposableManager();
}
