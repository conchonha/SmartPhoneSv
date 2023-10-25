package edu.abcd.smartphone.Screen.fragment.account;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;


import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.R;
import edu.abcd.smartphone.Screen.Adapter.AdapterSetting;
import edu.abcd.smartphone.Screen.fragment.BaseFragment;
import edu.abcd.smartphone.databinding.FragmentSettingBinding;
import edu.abcd.smartphone.domain.model.SettingDisplay;
import edu.abcd.smartphone.utils.Const;
import edu.abcd.smartphone.utils.SharePrefs;

@AndroidEntryPoint
public class FragmentSetting extends BaseFragment<FragmentSettingBinding> implements AdapterSetting.IActionSetting {
    private AdapterSetting adapterSetting;
    private SharePrefs sharePrefs;


    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharePrefs = new SharePrefs(requireContext());
        adapterSetting = new AdapterSetting(this, sharePrefs);

        binding.recyclerSetting.setAdapter(adapterSetting);
    }

    @Override
    public void onCheckedChange(SettingDisplay data) {
        switch (data.typeDisplayUI) {
            case TYPE_NOTIFICATION:
                sharePrefs.put(Const.KEY_NOTIFY, data.isChecked);
                break;
        }
    }

    @Override
    public void onClickListener(SettingDisplay data) {
        switch (data.typeDisplayUI) {
            case TYPE_REMOVE_CACHE:
                sharePrefs.deleteDir(requireContext().getCacheDir());
                adapterSetting.notifyList();
                break;
            case TYPE_LANGUAGE:

                break;
            case TYPE_SHARE:
                shareApp(requireActivity());
                break;
            case TYPE_PRIVACY:
                Navigation.findNavController(requireView()).navigate(R.id.fragmentWebView);
                break;
        }
    }

    public static void shareApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(
                Intent.EXTRA_TEXT,
                "BookMovieTickets at: https://play.google.com/store/apps/details?id=100003456304563"
        );
        context.startActivity(intent);
    }
}
