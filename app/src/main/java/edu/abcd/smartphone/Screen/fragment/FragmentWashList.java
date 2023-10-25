package edu.abcd.smartphone.Screen.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.R;
import edu.abcd.smartphone.Screen.Adapter.WashlistAdapter;
import edu.abcd.smartphone.data_source.remote.response.ProductRespose;
import edu.abcd.smartphone.databinding.FragmentWashlistBinding;
import edu.abcd.smartphone.domain.repositories.HomeRepository;
import edu.abcd.smartphone.utils.Const;

@AndroidEntryPoint
public class FragmentWashList extends BaseFragment<FragmentWashlistBinding>{
    private MutableLiveData<List<ProductRespose>> liveData = new MutableLiveData<>();
    private WashlistAdapter adapter;
    @Inject
    public HomeRepository homeRepository;

    @Inject
    public SharedPreferences preferences;

    @Override
    protected int getLayout() {
        return R.layout.fragment_washlist;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeRepository.productAPI(liveData);
        adapter = new WashlistAdapter();
        binding.rvWashList.setAdapter(adapter);

        liveData.observe(getViewLifecycleOwner(), productResposes -> {
            ArrayList<ProductRespose> listTmp = new ArrayList<>();
            for (ProductRespose respone: productResposes) {
                if (preferences.getBoolean(Const.KEY_FAVORITE + respone.id, false)){
                    listTmp.add(respone);
                }
            }
            adapter.updateItems(listTmp);
        });

        binding.edttimkim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.searchText(s.toString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        homeRepository.productAPI(liveData);
    }
}
