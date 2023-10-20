package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;

import edu.abcd.smartphone.R;
import edu.abcd.smartphone.databinding.ActivityRegisterBinding;

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
            startActivity(new Intent(RegisterActivity.this,Register2Activity.class));
        });
        binding.llLogin.setOnClickListener(v->{
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        });
    }
}