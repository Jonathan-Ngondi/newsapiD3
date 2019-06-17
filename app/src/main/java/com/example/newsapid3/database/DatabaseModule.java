package com.example.newsapid3.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Mugiwara_Munyi
 * @date 15/06/2019
 */
@Module
public class DatabaseModule {
    @Provides
    @Singleton
    static AppDatabase provideDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "savedarticles")
                .build();
    }
}
