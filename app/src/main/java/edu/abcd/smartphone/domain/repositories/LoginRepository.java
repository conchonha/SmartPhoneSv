package edu.abcd.smartphone.domain.repositories;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.body.LoginBody;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Singleton
public class LoginRepository {
    @Inject
    public DataServiceClient client;

    @Inject
    public LoginRepository() {
    }

    public void loginAPI(String pass, String email, MutableLiveData<LoginAccountRespose> liveData){
        client.login(new LoginBody(pass, email)).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<LoginAccountRespose> call, Response<LoginAccountRespose> response) {
                if (response.isSuccessful()) {
                    liveData.postValue(response.body());
                    Log.e("Retrofit", "Succ to make API call");
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginAccountRespose> call, Throwable t) {
                liveData.postValue(null);
                Log.e("Retrofit", "Failed to make API call", t);
            }
        });
    }
}
