package com.example.poweradapter.item;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public interface ItemRenderer<T extends RecyclerItem> {

    @LayoutRes int layoutRes();

    View createView(@NonNull ViewGroup parent);

    void render(@NonNull View itemView, @NonNull T item);
}
