package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.Screen.Home.MainActivity;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.body.RegisterBody;
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
        client.register(new RegisterBody("Sangtb@Email.com", "SangTB", "Fsoft@123456")).enqueue(new Callback<RegisterAccountResponse>() {
            @Override
            public void onResponse(Call<RegisterAccountResponse> call, Response<RegisterAccountResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegisterAccountResponse accountResponse = response.body();
                } else {

                }
            }

            @Override
            public void onFailure(Call<RegisterAccountResponse> call, Throwable t) {

            }
        });


        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });
        binding.llRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}