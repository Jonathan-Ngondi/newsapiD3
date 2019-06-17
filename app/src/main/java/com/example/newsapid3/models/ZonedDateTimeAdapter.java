package com.example.newsapid3.models;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.ZonedDateTime;

import javax.annotation.Nullable;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class ZonedDateTimeAdapter {

    @FromJson
    ZonedDateTime fromJson(String json){
        return ZonedDateTime.parse(json);
    }

    @ToJson
    String toJson(@Nullable ZonedDateTime value){
        return value != null? value.toString() : null;
    }

}
