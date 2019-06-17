package com.example.newsapid3.models;

import com.example.poweradapter.item.RecyclerItem;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.threeten.bp.ZonedDateTime;

import javax.annotation.Nullable;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@AutoValue
public abstract class NewsArticle implements RecyclerItem {

    @Nullable
    public abstract Source source();

    @Nullable
    public abstract String author();
    @Nullable
    public abstract String title();
    @Nullable
    public abstract String description();
    @Nullable
    public abstract String url();
    @Nullable
    public abstract String urlToImage();
    @Nullable
    public abstract ZonedDateTime publishedAt();
    @Nullable
    public abstract String content();

    public static JsonAdapter<NewsArticle> jsonAdapter(Moshi moshi){
        return new AutoValue_NewsArticle.MoshiJsonAdapter(moshi);
    }

    @Override
    public String renderKey() {
        return "Article";
    }






}
