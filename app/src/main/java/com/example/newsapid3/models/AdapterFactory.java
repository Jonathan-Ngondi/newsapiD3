package com.example.newsapid3.models;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@MoshiAdapterFactory
public abstract class AdapterFactory implements JsonAdapter.Factory {
    public static JsonAdapter.Factory create(){
        return new AutoValueMoshi_AdapterFactory();
    }
}
