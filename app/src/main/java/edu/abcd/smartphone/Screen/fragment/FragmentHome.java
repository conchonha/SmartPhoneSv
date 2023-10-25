package edu.abcd.smartphone.Screen.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.R;
import edu.abcd.smartphone.Screen.Adapter.CategoryAdapter;
import edu.abcd.smartphone.Screen.Adapter.ProductAdapter;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.databinding.FragmentHomeBinding;
import edu.abcd.smartphone.presentation.HomeViewModel;

@AndroidEntryPoint
public class FragmentHome extends BaseFragment<FragmentHomeBinding> {
    @Inject
    public DataServiceClient client;
    private ProductAdapter productAdapter;
    private CategoryAdapter adapterCategory;

    private HomeViewModel homeViewModel;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        listener();
    }

    private void init() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        productAdapter = new ProductAdapter();
        adapterCategory = new CategoryAdapter();

        homeViewModel.categoryAPI();
        homeViewModel.productAPI();

        binding.view1.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.view1.setAdapter(productAdapter);

        binding.rvCategories.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvCategories.setAdapter(adapterCategory);
    }

    private void listener() {
        homeViewModel.getLiveDataCategory().observe(requireActivity(), categoryResposes -> {
            if (categoryResposes != null) {
                adapterCategory.updateList(categoryResposes, homeViewModel);
            } else {
                showToast("Lỗi Ngoại lệ, Vui long thử lại sau");
            }
        });

        homeViewModel.getLiveDataProduct().observe(requireActivity(), productResposes -> {
            if (productResposes != null) {
                productAdapter.updateList(productResposes);
            } else {
                showToast("Lỗi Ngoại lệ, Vui long thử lại sau");
            }
        });

        binding.imageView8.setOnClickListener(v->{
            homeViewModel.productAPI();
        });

        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                productAdapter.searchText(s.toString());
            }
        });
    }

    public void showToast(String notification) {
        Toast.makeText(requireContext(), notification, Toast.LENGTH_SHORT).show();
    }
}


