package com.example.newsapid3.models;

import com.example.poweradapter.item.RecyclerItem;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import javax.annotation.Nullable;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@AutoValue
public abstract class Source implements RecyclerItem {

    @Nullable
    public abstract String id();
    @Nullable
    public abstract String name();

    @Override
    public String renderKey() {
        return "Source";
    }

    public static JsonAdapter<Source> jsonAdapter(Moshi moshi){
        return new AutoValue_Source.MoshiJsonAdapter(moshi);
    }



}
