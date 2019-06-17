package com.example.newsapid3.database.savedarticles;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * @author Mugiwara_Munyi
 * @date 15/06/2019
 */
@Entity
public class SavedArticle {

    @PrimaryKey
    @NonNull
    private final String headline;

    public SavedArticle(String headline) {this.headline = headline;}

    public String getHeadline(){return headline;}
}
