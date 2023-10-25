package edu.abcd.smartphone.domain.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.body.LoginBody;
import edu.abcd.smartphone.data_source.remote.response.CategoryRespose;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.data_source.remote.response.ProductRespose;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class HomeRepository {
    @Inject
    public DataServiceClient client;

    @Inject
    public HomeRepository() {
    }

    public void categoryAPI(MutableLiveData<List<CategoryRespose>> liveDataCategory) {
        client.category().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<CategoryRespose>> call, Response<List<CategoryRespose>> response) {
                if (response.isSuccessful()) {
                    Log.d("Retrofit", "Succ to make API call");
                    liveDataCategory.postValue(response.body());
                } else {
                    liveDataCategory.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<CategoryRespose>> call, Throwable t) {
                liveDataCategory.postValue(null);
                Log.e("Retrofit", "Failed to make API call", t);
            }
        });
    }

    public void productAPI(MutableLiveData<List<ProductRespose>> liveDataProduct) {
        client.product().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<ProductRespose>> call, Response<List<ProductRespose>> response) {
                if (response.isSuccessful()) {
                    liveDataProduct.postValue(response.body());
                    Log.e("Retrofit", "Succ to make API call");
                } else {
                    liveDataProduct.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<ProductRespose>> call, Throwable t) {
                liveDataProduct.postValue(null);
                Log.e("Retrofit", "Failed to make API call", t);
            }
        });
    }

    public void getProductListFromIdCategory(int id, MutableLiveData<List<ProductRespose>> liveDataProduct) {
        client.productFromIdCategory(id).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<ProductRespose>> call, Response<List<ProductRespose>> response) {
                if (response.isSuccessful()) {
                    liveDataProduct.postValue(response.body());
                    Log.e("Retrofit", "Succ to make API call");
                } else {
                    liveDataProduct.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<ProductRespose>> call, Throwable t) {
                liveDataProduct.postValue(null);
                Log.e("Retrofit", "Failed to make API call", t);
            }
        });
    }
}
