package edu.abcd.smartphone.Screen.Home;

import static edu.abcd.smartphone.utils.Const.KEY_PRODUCT_RESPONSE;
import static edu.abcd.smartphone.utils.Const.KEY_URL_IMAGE;
import static edu.abcd.smartphone.utils.Const.getUrlImage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.R;
import edu.abcd.smartphone.data_source.remote.response.ProductRespose;
import edu.abcd.smartphone.utils.Const;

@AndroidEntryPoint
public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, reviewTxt, scoreTxt;
    private ImageView picItem, backBtn, ivFavorite;
    private ProductRespose object;
    private int numberOrder = 1;

    @Inject
    public SharedPreferences.Editor editor;

    @Inject
    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        getBundle();
    }

    void getBundle() {
        object = (ProductRespose) getIntent().getSerializableExtra(KEY_PRODUCT_RESPONSE);

        Glide.with(this)
                .load(getUrlImage(object.image))
                .into(picItem);

        titleTxt.setText(object.name);
        feeTxt.setText("$" + object.getPrice());
        descriptionTxt.setText(object.getDescription());
//        reviewTxt.setText(object.getDescription());
//        scoreTxt.setText(object.getScore() + "");

        addToCartBtn.setOnClickListener(v -> {
//            object.setNumberInCart(numberOrder);
        });
        backBtn.setOnClickListener(v -> finish());

        updateUi();
        ivFavorite.setOnClickListener(v -> {
            editor.putBoolean(Const.KEY_FAVORITE + object.id, !checkFavorite()).apply();
            updateUi();
        });
    }

    private void updateUi() {
        if (checkFavorite()) {
            ivFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            ivFavorite.setImageResource(R.drawable.ic_favorite_none);
        }
    }

    public boolean checkFavorite() {
        return preferences.getBoolean(Const.KEY_FAVORITE + object.id, false);
    }

    private void initView() {
        ivFavorite = findViewById(R.id.ivlike);
        addToCartBtn = findViewById(R.id.addToCartBtn);
        feeTxt = findViewById(R.id.priceTxt);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        picItem = findViewById(R.id.itemPic);
        reviewTxt = findViewById(R.id.reviewTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        backBtn = findViewById(R.id.backBtn);
    }
}