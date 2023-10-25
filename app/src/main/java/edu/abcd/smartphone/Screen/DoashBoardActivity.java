package edu.abcd.smartphone.Screen;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.R;
import edu.abcd.smartphone.databinding.ActivityDoashBoardBinding;

@AndroidEntryPoint
public class DoashBoardActivity extends AppCompatActivity {
    private ActivityDoashBoardBinding binding;
    public static NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.bottomNavigate, navController);
        binding.bottomNavigate.setBackground(null);
        binding.bottomNavigate.setOnItemReselectedListener(item -> {
            navController.popBackStack(item.getItemId(),  false);
        });
    }
}