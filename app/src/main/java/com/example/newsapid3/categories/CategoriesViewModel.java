package com.example.newsapid3.categories;

import com.example.newsapid3.R;
import com.example.newsapid3.models.Category;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class CategoriesViewModel {
    private final BehaviorRelay<List<Category>> categoryRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();


    @Inject
    CategoriesViewModel(){

    }
    Observable<List<Category>> categories(){return categoryRelay;}
    Observable<Boolean> loading(){return loadingRelay;}
    Observable<Integer> error(){ return errorRelay; }


    Consumer<List<Category>> categoriesUpdated(){
        errorRelay.accept(-1);
        return categoryRelay;
    }

    Consumer<Boolean> loadingUpdated(){
        return loadingRelay;
    }

    Consumer<Throwable> onError(){
        return throwable -> {
            Timber.e(throwable, "Error loading Categories");
            errorRelay.accept(R.string.constants_categories_error);
        };
    }

}
