package edu.abcd.smartphone.domain.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.body.LoginBody;
import edu.abcd.smartphone.data_source.remote.body.RegisterBody;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.data_source.remote.response.RegisterAccountResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {
    public DataServiceClient client;

    public RegisterRepository(DataServiceClient client) {
        this.client = client;
    }

    public void registerAPI(String email, String name, String pass, MutableLiveData<RegisterAccountResponse> liveData){
        client.register(new RegisterBody(email, name, pass)).enqueue(new Callback<RegisterAccountResponse>() {
            @Override
            public void onResponse(Call<RegisterAccountResponse> call, Response<RegisterAccountResponse> response) {
                if(response.isSuccessful()){
                    liveData.postValue(response.body());
                    Log.e("Retrofit", "Succ to make API call");
                }else {
                    liveData.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<RegisterAccountResponse> call, Throwable t) {
                liveData.postValue(null);
                Log.e("Retrofit", "Failed to make API call", t);
            }
        });
    }
}
