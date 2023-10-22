package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.R;
import edu.abcd.smartphone.Screen.Home.MainActivity;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.response.RegisterAccountResponse;
import edu.abcd.smartphone.databinding.ActivityRegister2Binding;
import edu.abcd.smartphone.presentation.RegisterViewModel;

@AndroidEntryPoint
public class Register2Activity extends AppCompatActivity {
    @Inject
    public DataServiceClient client;
    private ActivityRegister2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegister2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(v -> {
            Intent intent = getIntent();
            String email = intent.getStringExtra("email");
            String name = intent.getStringExtra("name");
            String pass = binding.edtPassword.getText().toString().trim();
            Log.d("register", "onCreate: " + email + name + pass);
            initViewModel(email, name, pass);
        });
    }

    public void initViewModel(String email, String name, String pass) {
        if (checkIsEmpty(email, name, pass)) {
            RegisterViewModel registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
            registerViewModel.registerAPI(email, name, pass);
            registerViewModel.getLiveData().observe(this, new Observer<RegisterAccountResponse>() {
                @Override
                public void onChanged(RegisterAccountResponse registerAccountResponse) {
                    if (registerAccountResponse != null) {
                        notificationRegister("Email: " + registerAccountResponse.getEmail());
                        startActivity(new Intent(Register2Activity.this, LoginActivity.class));
                    }else {
                        notificationRegister("Dang ky that bai");
                    }
                }
            });
        }
    }

    public boolean checkIsEmpty(String email, String name, String pass) {
        if (TextUtils.isEmpty(email)|| TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
            notificationRegister("Empty!");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            notificationRegister("Invalid email format!");
            return false;
        }
        // If all checks pass, return true
        return true;
    }

    public void notificationRegister(String notification){
        Toast.makeText(Register2Activity.this, notification, Toast.LENGTH_SHORT).show();
    }

}