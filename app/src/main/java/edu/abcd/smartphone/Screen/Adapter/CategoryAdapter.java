package edu.abcd.smartphone.Screen.Adapter;

import static edu.abcd.smartphone.utils.Const.getUrlImage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;
import java.util.List;


import edu.abcd.smartphone.R;
import edu.abcd.smartphone.data_source.remote.response.CategoryRespose;
import edu.abcd.smartphone.presentation.HomeViewModel;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {
    List<CategoryRespose> items = new ArrayList<>();
    Context context;
    HomeViewModel homeViewModel;

    public void updateList(List<CategoryRespose> items, HomeViewModel vm) {
        this.items = items;
        this.homeViewModel = vm;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        CategoryRespose categoryRespose = items.get(position);
        holder.txtTitle.setText(categoryRespose.getCategory_name());

        Glide.with(holder.itemView.getContext())
                .load(getUrlImage(categoryRespose.image))
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            homeViewModel.getProductListFromIdCategory(categoryRespose.id);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView pic;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.tvTitle);
            pic = itemView.findViewById(R.id.imgView);
        }
    }
}
