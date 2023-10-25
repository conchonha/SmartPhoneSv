package edu.abcd.smartphone.Screen.Adapter;

import static edu.abcd.smartphone.utils.Const.KEY_PRODUCT_RESPONSE;
import static edu.abcd.smartphone.utils.Const.getUrlImage;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;
import java.util.List;

import edu.abcd.smartphone.R;
import edu.abcd.smartphone.Screen.Home.DetailActivity;
import edu.abcd.smartphone.data_source.remote.response.ProductRespose;
import edu.abcd.smartphone.databinding.ItemSanphamBinding;

public class WashlistAdapter extends BaseRecyclerViewAdapter<ProductRespose, ItemSanphamBinding> {
    private List<ProductRespose> listSearch = new ArrayList<>();

    @Override
    public void updateItems(List<ProductRespose> list) {
        super.updateItems(list);
        listSearch = list;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_sanpham;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<ItemSanphamBinding> holder, int position) {
        ProductRespose respose = mListItem.get(position);

        Glide.with(holder.itemView.getContext())
                .load(getUrlImage(respose.image))
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.binding.imgHinh);

        holder.binding.txtName.setText(respose.getName());
        holder.binding.txtGia.setText("$" + respose.getPrice());
        holder.binding.txtNgay.setText(respose.getDescription());
        holder.binding.llGroup.setOnClickListener(v->{
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra(KEY_PRODUCT_RESPONSE, respose);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    public void searchText(String toString) {
        if (toString.equals("")) {
            mListItem = listSearch;
            notifyDataSetChanged();
            return;
        }
        ArrayList<ProductRespose> listTmp = new ArrayList<>();
        for (ProductRespose pr : listSearch) {
            if (pr.name.toLowerCase().contains(toString.toLowerCase())) {
                listTmp.add(pr);
            }
        }
        mListItem = listTmp;
        notifyDataSetChanged();
    }
}
