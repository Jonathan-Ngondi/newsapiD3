package com.example.poweradapter.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private final RecyclerDataSource dataSource;

    public RecyclerAdapter(RecyclerDataSource dataSource){
        this.dataSource = dataSource;
        dataSource.attachToAdapter(this);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(parent, dataSource.rendererForType(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        holder.bind(dataSource.getItem(i));
    }

    @Override
    public int getItemCount() {
        return dataSource.getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return dataSource.viewResourceForPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return dataSource.getItem(position).getId();
    }
}
