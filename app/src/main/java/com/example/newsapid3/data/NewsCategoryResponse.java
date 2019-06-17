package com.example.newsapid3.data;

import com.example.newsapid3.models.NewsArticle;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@AutoValue
public abstract class NewsCategoryResponse {

    public abstract List<NewsArticle> articles();

    public static JsonAdapter<NewsCategoryResponse> jsonAdapter(Moshi moshi){
        return new AutoValue_NewsCategoryResponse.MoshiJsonAdapter(moshi);
    }
}
