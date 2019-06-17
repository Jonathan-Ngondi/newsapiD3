package com.example.poweradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.poweradapter.item.ItemRenderer;
import com.example.poweradapter.item.RecyclerItem;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private final ItemRenderer<RecyclerItem> renderer;

    public RecyclerViewHolder(ViewGroup parent, ItemRenderer<RecyclerItem> renderer) {
        super(renderer.createView(parent));
        this.renderer = renderer;
    }

    void bind(RecyclerItem item){ renderer.render(itemView, item);}
}
