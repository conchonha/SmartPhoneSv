package edu.abcd.smartphone.Screen.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T , VB extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder<VB>>{
    protected List<T> mListItem = new ArrayList();

    public void updateItems(List<T> list) {
        this.mListItem = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayout(), parent, false));
    }

    protected abstract int getLayout();

}
