package edu.abcd.smartphone.presentation;

import android.app.Application;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.response.RegisterAccountResponse;
import edu.abcd.smartphone.domain.repositories.RegisterRepository;

@HiltViewModel
public class RegisterViewModel extends ViewModel {
    private MutableLiveData<RegisterAccountResponse> liveData;

    @Inject
    public DataServiceClient dataServiceClient;

    @Inject
    public Application application;


    @Inject
    public RegisterViewModel() {
        liveData = new MutableLiveData();
    }

    public MutableLiveData<RegisterAccountResponse> getLiveData() {
        return liveData;
    }

    public void registerAPI(String email, String name, String pass) {
        if (checkIsEmpty(email, name, pass)) {
            RegisterRepository registerRepository = new RegisterRepository(dataServiceClient);
            registerRepository.registerAPI(email, name, pass, liveData);
        }
    }

    private boolean checkIsEmpty(String email, String name, String pass) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
            showToast("Empty!");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Invalid email format!");
            return false;
        }
        // If all checks pass, return true
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show();
    }
}
