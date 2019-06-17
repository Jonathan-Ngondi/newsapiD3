package com.example.newsapid3.database.savedarticles;

import com.example.newsapid3.database.AppDatabase;
import com.example.newsapid3.models.NewsArticle;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
public class SaveArticleService {

    private final AppDatabase appDatabase;
    private final BehaviorRelay<Set<String>> favoritedArticlesRelay = BehaviorRelay.createDefault(new HashSet<>());


    @Inject
    SaveArticleService(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
        appDatabase.savedArticleDao().getSavedArticles()
                .subscribeOn(Schedulers.io())
                .map(savedArticles ->{
                    Set<String> headlines = new HashSet();
                    for(SavedArticle article: savedArticles){
                        headlines.add(article.getHeadline());
                    }
                    return headlines;
                        })
                .subscribe(favoritedArticlesRelay, throwable -> Timber.e(throwable, "Error getting favorited Articles"));
    }

    public Observable<Set<String>> favoritedArticles(){ return favoritedArticlesRelay;}


    public void toggleFavorites(NewsArticle article){
        runDbOp(() ->{
            if(favoritedArticlesRelay.getValue().contains(article.title())){
                deleteSavedArticles(article);
            } else {
                addSavedArticles(article);
            }
        });
    }


    private void addSavedArticles(NewsArticle article) {
        appDatabase.savedArticleDao().addSavedArticle(new SavedArticle(article.title()));
    }

    private void deleteSavedArticles(NewsArticle article) {
        appDatabase.savedArticleDao().deleteSavedArticle(new SavedArticle(article.title()));
    }


    private void runDbOp(Action action){
        Completable.fromAction(action)
                .subscribeOn(Schedulers.io())
                .subscribe(()-> {}, throwable -> Timber.e(throwable, "Error performing database operations"));
    }
}
