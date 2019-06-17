package com.example.newsapid3.data;

import com.example.newsapid3.BuildConfig;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public interface CategoryService {

    @Headers({"X-Api-Key:"+ BuildConfig.API_KEY})
    @GET("top-headlines?country=us")
    Single<NewsCategoryResponse>    getNewsCategory(@Query("category") String category);
}
