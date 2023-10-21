package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.Screen.Home.MainActivity;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.body.LoginBody;
import edu.abcd.smartphone.data_source.remote.body.RegisterBody;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.data_source.remote.response.RegisterAccountResponse;
import edu.abcd.smartphone.databinding.ActivityLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    @Inject
    public DataServiceClient client;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v -> {
            callAPI();
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });
        binding.llRegister.setOnClickListener(v -> {
//            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
    public void callAPI(){
        client.login(new LoginBody("123456", "xuanqn01@gmail.com")).enqueue(new Callback<LoginAccountRespose>() {
            @Override
            public void onResponse(Call<LoginAccountRespose> call, Response<LoginAccountRespose> response) {
                Toast.makeText(LoginActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            @Override
            public void onFailure(Call<LoginAccountRespose> call, Throwable t) {
                Log.e("Retrofit", "Failed to make API call", t);
                Toast.makeText(LoginActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}