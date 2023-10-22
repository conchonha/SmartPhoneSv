package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.R;
import edu.abcd.smartphone.Screen.Home.DetailActivity;
import edu.abcd.smartphone.Screen.Home.MainActivity;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.data_source.remote.response.RegisterAccountResponse;
import edu.abcd.smartphone.databinding.ActivityRegisterBinding;
import edu.abcd.smartphone.presentation.LoginViewModel;
import edu.abcd.smartphone.presentation.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.edtBirthday.setOnClickListener(v->{
            MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
            builder.setTitleText("Chọn ngày");
            MaterialDatePicker materialDatePicker = builder.build();
            materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                String format = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                binding.edtBirthday.setText(simpleDateFormat.format(materialDatePicker.getSelection()));
            });
        });

        binding.btnRegister.setOnClickListener(v->{
            String email = binding.edtEmail.getText().toString().trim();
            String username = binding.edtName.getText().toString().trim();

            Intent intent= new Intent(RegisterActivity.this, Register2Activity.class);
            intent.putExtra("email", email);
            intent.putExtra("name", username);
            startActivity(intent);
        });
        binding.llLogin.setOnClickListener(v->{
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        });
    }
}