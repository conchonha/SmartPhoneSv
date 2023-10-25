package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.databinding.ActivityRegister2Binding;
import edu.abcd.smartphone.presentation.RegisterViewModel;

@AndroidEntryPoint
public class Register2Activity extends AppCompatActivity {
    @Inject
    public DataServiceClient client;
    RegisterViewModel registerViewModel;

    private ActivityRegister2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        listener();
    }

    private void init() {
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
    }

    private void listener() {
        binding.btnRegister.setOnClickListener(v -> {
            registerAccount();
        });

        binding.llLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    public void registerAccount() {
        String email = binding.edtEmail.getText().toString();
        String name = binding.edtName.getText().toString();
        String pass = binding.edtPassword.getText().toString();

        registerViewModel.registerAPI(email, name, pass);
        registerViewModel.getLiveData().observe(this, registerAccountResponse -> {
            if (registerAccountResponse != null) {
                showToast("Register Success");
                startActivity(new Intent(Register2Activity.this, LoginActivity.class));
            } else {
                showToast("Dang ky that bai");
            }
        });
    }


    public void showToast(String notification) {
        Toast.makeText(Register2Activity.this, notification, Toast.LENGTH_SHORT).show();
    }

}