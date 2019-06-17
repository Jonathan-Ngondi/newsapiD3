package com.example.newsapid3.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Mugiwara_Munyi
 * @date 16/06/2019
 */
@Module
public class TestDatabaseModule {

    @Provides
    @Singleton
    static AppDatabase provideDatabase(Context context) {
        return Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
    }
}
