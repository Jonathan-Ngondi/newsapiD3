package com.example.newsapid3.models;

import com.example.poweradapter.item.RecyclerItem;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@AutoValue
public abstract class Category implements RecyclerItem {

    public abstract Long id();

    public abstract String name();

    @Override
    public long getId() {
        return id();
    }

    @Override
    public String renderKey() {
        return "Category";
    }

    public static JsonAdapter<Category> jsonAdapter(Moshi moshi){
        return new AutoValue_Category.MoshiJsonAdapter(moshi);
    }
}
