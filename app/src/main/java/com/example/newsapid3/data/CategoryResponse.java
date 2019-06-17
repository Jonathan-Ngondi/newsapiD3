package com.example.newsapid3.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@AutoValue
public abstract class CategoryResponse {

    public abstract List<CategoryResponse> categories();

    public static JsonAdapter<CategoryResponse> jsonAdapter(Moshi moshi){
        return new AutoValue_CategoryResponse.MoshiJsonAdapter(moshi);
    }
}
