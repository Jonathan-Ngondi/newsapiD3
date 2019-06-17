package com.example.newsapid3.categories;

import com.example.newsapid3.models.Category;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@AutoValue
public abstract class CategoriesResponse {

    public abstract List<Category> categories();

    public static JsonAdapter<CategoriesResponse> jsonAdapter(Moshi moshi){
        return new AutoValue_CategoriesResponse.MoshiJsonAdapter(moshi);
    }
}
