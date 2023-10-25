package edu.abcd.smartphone.Screen.fragment.account;

import static edu.abcd.smartphone.Screen.fragment.account.FragmentSetting.shareApp;
import static edu.abcd.smartphone.utils.Const.KEY_USER;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import edu.abcd.smartphone.R;
import edu.abcd.smartphone.Screen.LoginActivity;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.databinding.FragmentAccountBinding;
import edu.abcd.smartphone.utils.SharePrefs;

public class FragmentProfile extends Fragment {
    private FragmentAccountBinding binding;
    private SharePrefs sharePrefs;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharePrefs = new SharePrefs(requireContext());
        LoginAccountRespose respose = sharePrefs.getUser();
        if (respose != null){
            binding.tvName.setText(respose.name);
            binding.tvEmail.setText(respose.email);
        }

        binding.rowClearCache.relativeGroup.setOnClickListener(view1 -> {
            sharePrefs.deleteDir(requireContext().getCacheDir());
        });

        binding.rowSetting.relativeGroup.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.fragmentSetting);
        });

        binding.rowLogout.relativeGroup.setOnClickListener(v -> {
            sharePrefs.remove(KEY_USER);
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        binding.rowPolicy.relativeGroup.setOnClickListener(v->{
            Navigation.findNavController(requireView()).navigate(R.id.fragmentWebView);
        });

        binding.rowShareApp.relativeGroup.setOnClickListener(v->{
            shareApp(requireActivity());
        });
    }
}
