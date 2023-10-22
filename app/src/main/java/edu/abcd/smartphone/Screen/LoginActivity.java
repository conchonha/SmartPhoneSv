package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
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
            String pass = binding.edtPassword.getText().toString().trim();
            String username = binding.edtEmail.getText().toString().trim();
            initViewModel(pass, username);
        });
        binding.llRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    public void initViewModel(String pass, String username) {
        if (checkIsEmpty(pass, username)) {
            LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
            loginViewModel.getLiveData().observe(this, new Observer<LoginAccountRespose>() {
                @Override
                public void onChanged(LoginAccountRespose loginAccountRespose) {
                    if (loginAccountRespose != null) {
                        notificationLogin("Email: " + loginAccountRespose.getEmail());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        notificationLogin("Check lai email va pass!");
                    }
                }
            });
            loginViewModel.loginAPI(pass, username);
        }
    }

    public boolean checkIsEmpty(String pass, String username) {
        if (TextUtils.isEmpty(username)) {
            notificationLogin("Username is empty!");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            notificationLogin("Invalid email format!");
            return false;
        }else if (TextUtils.isEmpty(pass)) {
            notificationLogin("Password is empty!");
            return false;
        }

        // If all checks pass, return true
        return true;
    }

    public void notificationLogin(String notification){
        Toast.makeText(LoginActivity.this, notification, Toast.LENGTH_SHORT).show();
    }

}