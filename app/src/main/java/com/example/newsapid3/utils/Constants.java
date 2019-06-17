package com.example.newsapid3.utils;

import com.example.newsapid3.categories.CategoriesResponse;
import com.example.newsapid3.models.AdapterFactory;
import com.example.newsapid3.models.Category;
import com.example.newsapid3.models.ZonedDateTimeAdapter;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import io.reactivex.Single;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class Constants {


    private static final Moshi CONSTANT_MOSHI = initializeMoshi();
    private static Constants INSTANCE = new Constants();

    public static Single<List<Category>> homeCategories(){
        CategoriesResponse response = loadJson("mock/categories/get_categories.json", CategoriesResponse.class);
        List<Category> result = response.categories();
        return Single.just(result);

    }

    public static <T> T loadJson(String path, Class clazz){
        try{
            String json = getFileString(path);
            //noinspection unchecked
            return (T) CONSTANT_MOSHI.adapter(clazz).fromJson(json);
        } catch (IOException e){
            throw new IllegalArgumentException("Could not deserialize: "+path+" into type "+clazz);
        }
    }

    private static String getFileString(String path) {
        try{
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    INSTANCE.getClass().getClassLoader().getResourceAsStream(path)));
            String line;
            while((line = reader.readLine())!=null){
                sb.append(line);

            }
            return sb.toString();
        } catch(IOException e){
            throw new IllegalArgumentException("Could not read from resource at: " + path);
        }
    }



    private static Moshi initializeMoshi() {
        Moshi.Builder builder = new Moshi.Builder();
        builder.add(AdapterFactory.create());
        builder.add(new ZonedDateTimeAdapter());
        return builder.build();

    }


}
