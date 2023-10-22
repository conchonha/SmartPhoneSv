package edu.abcd.smartphone.Adapter;

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

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import edu.abcd.smartphone.R;
import edu.abcd.smartphone.data_source.remote.response.CategoryRespose;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {
    ArrayList<CategoryRespose> items;
    Context context;

    public CategoryAdapter(ArrayList<CategoryRespose> items) {
        this.items = items;
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
        holder.txtTitle.setText(items.get(position).getCategory_name());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getImage(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.pic);

//        holder.itemView.setOnClickListener(v -> {
//            Intent intent=new Intent(holder.itemView.getContext(), DetailActivity.class);
//            intent.putExtra("object",items.get(position));
//            holder.itemView.getContext().startActivity(intent);
//        });
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
