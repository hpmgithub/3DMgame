package com.jash.dmgame.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;
/**
 * Created by jash
 * Date: 15-11-20
 * Time: 下午2:53
 */
public class BindingAdapter<T> extends RecyclerView.Adapter<BindingAdapter.BindingHolder> {
    private List<T> list;
    private int item_layout;
    private int variable_id;
    private View.OnClickListener onClickListener;

    public BindingAdapter(List<T> list, int item_layout, int variable_id) {
        this.list = list;
        this.item_layout = item_layout;
        this.variable_id = variable_id;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), item_layout, parent, false);
        binding.getRoot().setOnClickListener(onClickListener);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.binding.setVariable(variable_id, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void addAll(Collection<? extends T> collection){
        int size = list.size();
        list.addAll(collection);
        notifyItemRangeInserted(size, collection.size());
    }

    public void clear(){
        int size = list.size();
        list.clear();
        notifyItemRangeRemoved(0, size);
    }
    public static class BindingHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding binding;

        public BindingHolder(ViewDataBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
