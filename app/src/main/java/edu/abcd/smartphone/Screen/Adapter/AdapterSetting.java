package edu.abcd.smartphone.Screen.Adapter;

import android.app.Application;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

import edu.abcd.smartphone.R;
import edu.abcd.smartphone.app.MyApplication;
import edu.abcd.smartphone.databinding.ItemRecyclerSettingBinding;
import edu.abcd.smartphone.domain.model.SettingDisplay;
import edu.abcd.smartphone.domain.model.TypeDisplayUI;
import edu.abcd.smartphone.utils.Const;
import edu.abcd.smartphone.utils.SharePrefs;

public class AdapterSetting extends BaseRecyclerViewAdapter<SettingDisplay, ItemRecyclerSettingBinding> {
    private IActionSetting action;
    static private SharePrefs sharePrefs;


    public AdapterSetting(IActionSetting iActionSetting,SharePrefs sharePrefs){
        this.action = iActionSetting;
        this.sharePrefs = sharePrefs;
        notifyList();
    }

    public void notifyList(){
        updateItems(new ArrayList(Arrays.asList(getListSetting())));
    }

    @Override
    protected int getLayout() {
        return R.layout.item_recycler_setting;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<ItemRecyclerSettingBinding> holder, int position) {
        SettingDisplay settingDisplay = mListItem.get(position);

        holder.binding.setData(settingDisplay);
        holder.binding.swipe.setOnCheckedChangeListener((view, b) -> {
            settingDisplay.isChecked = b;
            action.onCheckedChange(settingDisplay);
        });

        holder.binding.getRoot().setOnClickListener(v -> {
            action.onClickListener(settingDisplay);
        });
    }

    public interface IActionSetting{
        public void onCheckedChange(SettingDisplay settingDisplay);
        public void onClickListener(SettingDisplay settingDisplay);
    }

    public static SettingDisplay[] getListSetting() {
        Application context = MyApplication.application;
        return new SettingDisplay[]{new SettingDisplay(R.string.overview, TypeDisplayUI.TYPE_OVERVIEW, R.color.h2CB252, ""),
                new SettingDisplay(R.string.notification, TypeDisplayUI.TYPE_NOTIFICATION, R.color.colorStroke, "", false, true, sharePrefs.getSharedPref().getBoolean(Const.KEY_NOTIFY, false)),
                new SettingDisplay(R.string.display, TypeDisplayUI.TYPE_DEFAULT_SYSTEM, R.color.colorStroke, context.getString(R.string.default_system)),
                new SettingDisplay(R.string.language, TypeDisplayUI.TYPE_LANGUAGE, R.color.colorStroke, context.getString(R.string.vietnam)),
                new SettingDisplay(R.string.remove_cache, TypeDisplayUI.TYPE_REMOVE_CACHE, R.color.colorStroke, sharePrefs.calculateSizeRecursively(context) + " KB", true, false),
                new SettingDisplay(R.string.hotline, TypeDisplayUI.HOT_LINE_PHONE, R.color.h2CB252, ""),
                new SettingDisplay(R.string.hotline_phone, TypeDisplayUI.HOT_LINE_PHONE, R.color.colorStroke, "", true, false),
                new SettingDisplay(R.string.other, TypeDisplayUI.HOT_LINE_PHONE, R.color.h2CB252, ""),
                new SettingDisplay(R.string.version, TypeDisplayUI.TYPE_VERSION, R.color.colorStroke, "1.0"),
                new SettingDisplay(R.string.share, TypeDisplayUI.TYPE_SHARE, R.color.colorStroke, ""),
                new SettingDisplay(R.string.privacy, TypeDisplayUI.TYPE_PRIVACY, R.color.colorStroke, "")};
    }
}