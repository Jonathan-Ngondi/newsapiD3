package com.example.newsapid3.data;

import com.example.newsapid3.models.Category;
import com.example.newsapid3.models.NewsArticle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@Singleton
public class CategoryRepository {

    private final Provider<CategoryRequester> categoryRequesterProvider;
    private final List<Category> cachedCategories = new ArrayList<>();
    private final Map<String, List<NewsArticle>> cachedArticles = new HashMap<>();
    private final Scheduler scheduler;

    @Inject
    CategoryRepository(Provider<CategoryRequester> categoryRequesterProvider,
                       @Named("network_scheduler") Scheduler scheduler){
        this.categoryRequesterProvider = categoryRequesterProvider;
        this.scheduler = scheduler;
    }

    public Single<List<Category>> getCategories(){
        return Maybe.concat(cachedCategories(), apiCategories())
                .firstOrError()
                .subscribeOn(scheduler);
    }

    public Maybe<List<Category>> apiCategories() {
        return categoryRequesterProvider.get().getCategories()
                .doOnSuccess(categories -> {
                    cachedCategories.clear();
                    cachedCategories.addAll(categories);
                })
                .toMaybe();
    }

    public Maybe<List<Category>> cachedCategories(){
        return Maybe.create( e ->{
            if(!cachedCategories.isEmpty()){
                e.onSuccess(cachedCategories);
            }
            e.onComplete();

        });

    }

    public Single<List<NewsArticle>> getNewsArticles(String category){
        return Maybe.concat(cachedArticles(category), apiArticles(category))
                .firstOrError()
                .subscribeOn(scheduler);
    }

    private Maybe<List<NewsArticle>> cachedArticles(String category){
        return Maybe.create(e->{
            if(cachedArticles.containsKey(category)){
                e.onSuccess(cachedArticles.get(category));
            }
            e.onComplete();
        });
    }



    private Maybe<List<NewsArticle>> apiArticles(String category){
        return categoryRequesterProvider.get().getArticles(category)
                .doOnSuccess(articles -> cachedArticles.put(category, articles))
                .toMaybe();
    }

    public void clearCache() {
        cachedArticles.clear();
    }
}
