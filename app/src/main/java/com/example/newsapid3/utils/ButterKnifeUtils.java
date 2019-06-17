package com.example.newsapid3.utils;

import butterknife.Unbinder;
import timber.log.Timber;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class ButterKnifeUtils {

    private ButterKnifeUtils(){

    }

    public static void unbind(Unbinder unbinder){
        if(unbinder !=null){
            try{
                unbinder.unbind();
            } catch (IllegalStateException e){
                Timber.e(e, "Error unbinding views");
            }
        }
    }
}
