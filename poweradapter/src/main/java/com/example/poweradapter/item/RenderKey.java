package com.example.poweradapter.item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */

@MapKey
@Target(ElementType.METHOD)
public @interface RenderKey {
    String value();
}
