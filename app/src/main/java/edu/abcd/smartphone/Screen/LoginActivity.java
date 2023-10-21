package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.Screen.Home.MainActivity;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.databinding.ActivityLoginBinding;
import edu.abcd.smartphone.presentation.LoginViewModel;

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
            initViewModel();
        });
        binding.llRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    public void initViewModel(){
        //chưa xét ngoại lệ đăng nhập và chưa post từ giao diện
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getLiveData().observe(this, new Observer<LoginAccountRespose>() {
            @Override
            public void onChanged(LoginAccountRespose loginAccountRespose) {
                if(loginAccountRespose !=null){
                    Toast.makeText(LoginActivity.this, "Email: " + loginAccountRespose.getEmail(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginViewModel.loginAPI();
    }
}