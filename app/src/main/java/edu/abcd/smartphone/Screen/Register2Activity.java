package edu.abcd.smartphone.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.abcd.smartphone.R;
import edu.abcd.smartphone.databinding.ActivityRegister2Binding;

public class Register2Activity extends AppCompatActivity {
    private ActivityRegister2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegister2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}