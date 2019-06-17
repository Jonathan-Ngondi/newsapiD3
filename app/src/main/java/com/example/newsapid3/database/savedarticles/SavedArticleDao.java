package com.example.newsapid3.database.savedarticles;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author Mugiwara_Munyi
 * @date 15/06/2019
 */
@Dao
public interface SavedArticleDao {

    @Query("SELECT * from savedarticle")
    Flowable<List<SavedArticle>> getSavedArticles();

    @Query("SELECT count(*) from savedarticle")
    int getCount();

    @Insert
    void addSavedArticle(SavedArticle savedArticle);

    @Delete
    void deleteSavedArticle(SavedArticle savedArticle);

    @Query("DELETE from savedarticle")
    void clearDb();

}
