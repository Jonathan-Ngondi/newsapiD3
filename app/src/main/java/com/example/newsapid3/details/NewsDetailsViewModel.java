package com.example.newsapid3.details;

import com.example.newsapid3.R;
import com.example.newsapid3.di.ScreenScope;
import com.jakewharton.rxrelay2.BehaviorRelay;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;


/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@ScreenScope
public class NewsDetailsViewModel {

    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();


    @Inject NewsDetailsViewModel(){

    }

    Observable<Boolean> loading(){ return loadingRelay;}

    Observable<Integer> error() {return errorRelay;}

    Action articlesUpdated(){
        return () -> errorRelay.accept(-1);
    }

    Consumer<Boolean> loadingUpdated(){
        return loadingRelay;
    }

    Consumer<Throwable> onError(){
        return throwable -> {
            Timber.e(throwable, "Error loading Articles");
            errorRelay.accept(R.string.api_error_loading_articles);
        };
    }

}
