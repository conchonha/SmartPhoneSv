package edu.abcd.smartphone.Screen.Adapter;

import static edu.abcd.smartphone.utils.Const.KEY_PRODUCT_RESPONSE;
import static edu.abcd.smartphone.utils.Const.KEY_URL_IMAGE;
import static edu.abcd.smartphone.utils.Const.getUrlImage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import java.util.function.Consumer;

import edu.abcd.smartphone.R;
import edu.abcd.smartphone.Screen.Home.DetailActivity;
import edu.abcd.smartphone.data_source.remote.response.ProductRespose;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Viewholder> {
    List<ProductRespose> items = new ArrayList<>();
    List<ProductRespose> itemsSearch = new ArrayList<>();
    Context context;

    public void updateList(List<ProductRespose> items) {
        this.items = items;
        this.itemsSearch = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pup_list, parent, false);
        return new Viewholder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ProductRespose productRespose = items.get(position);

        holder.titleTxt.setText(productRespose.getName());
        holder.feeTxt.setText("$" + productRespose.getPrice());
        holder.scoreTxt.setText("" + productRespose.getScore());


        Glide.with(holder.itemView.getContext())
                .load(getUrlImage(productRespose.image))
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra(KEY_PRODUCT_RESPONSE, productRespose);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void searchText(String toString) {
        if (toString.equals("")) {
            items = itemsSearch;
            notifyDataSetChanged();
            return;
        }
        ArrayList<ProductRespose> listTmp = new ArrayList<>();
        for (ProductRespose pr : itemsSearch) {
            if (pr.name.toLowerCase().contains(toString.toLowerCase())) {
                listTmp.add(pr);
            }
        }
        items = listTmp;
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt, feeTxt, scoreTxt;
        ImageView pic;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.titleTxt);
            feeTxt = itemView.findViewById(R.id.feeTxt);
            scoreTxt = itemView.findViewById(R.id.scoreTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
