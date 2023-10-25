package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.databinding.ActivityLoginBinding;
import edu.abcd.smartphone.presentation.LoginViewModel;
import edu.abcd.smartphone.utils.Const;
import edu.abcd.smartphone.utils.SharePrefs;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Inject
    public DataServiceClient client;

    @Inject
    public SharedPreferences preferences;
    public LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (preferences.getBoolean(Const.IS_SAVE_PASS, false)){
            startMainActivity();
        }

        binding.btnLogin.setOnClickListener(v -> {
            String pass = binding.edtPassword.getText().toString().trim();
            String username = binding.edtEmail.getText().toString().trim();
            initViewModel(pass, username);
        });

        binding.llRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, Register2Activity.class)));
    }

    public void initViewModel(String pass, String username) {
        loginViewModel.loginAPI(pass, username);
        loginViewModel.getLiveData().observe(this, loginAccountRespose -> {
            if (loginAccountRespose != null) {
                showToast("Login Success");
                SharePrefs sharePrefs = new SharePrefs(this);
                sharePrefs.put(Const.KEY_USER,loginAccountRespose);
                loginViewModel.checkSavePass(binding.chkRemember.isChecked());
                startMainActivity();
            } else {
                showToast("Login Fail: account do not exit or fail server");
            }
        });
    }


    private void showToast(String notification) {
        Toast.makeText(LoginActivity.this, notification, Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity(){
        startActivity(new Intent(LoginActivity.this, DoashBoardActivity.class));
        finish();
    }
}