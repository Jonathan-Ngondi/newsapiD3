package com.example.newsapid3.lifecycle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class DisposableManager {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void add(Disposable... disposables){compositeDisposable.addAll(disposables);}
    public void dispose(){compositeDisposable.clear();}
}
