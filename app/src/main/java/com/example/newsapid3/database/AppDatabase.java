package com.example.newsapid3.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.newsapid3.database.savedarticles.SavedArticle;
import com.example.newsapid3.database.savedarticles.SavedArticleDao;

/**
 * @author Mugiwara_Munyi
 * @date 15/06/2019
 */
@Database(entities = SavedArticle.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase db;

    private static SavedArticleDao dbInstance;

    public abstract SavedArticleDao savedArticleDao();

    public static SavedArticleDao getInstance(Context context){
        if(dbInstance==null){
            db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();
            dbInstance = db.savedArticleDao();
        }
        return dbInstance;
    }
}
