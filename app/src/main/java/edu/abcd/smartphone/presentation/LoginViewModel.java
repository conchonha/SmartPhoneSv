package edu.abcd.smartphone.presentation;

import static edu.abcd.smartphone.utils.Const.IS_SAVE_PASS;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.domain.repositories.LoginRepository;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    @Inject
    public DataServiceClient dataServiceClient;

    @Inject
    public Application application;

    @Inject
    public LoginRepository loginRepository;

    @Inject
    public SharedPreferences.Editor editor;

    MutableLiveData<LoginAccountRespose> liveData;

    @Inject
    public LoginViewModel() {
        liveData = new MutableLiveData();
    }

    public MutableLiveData<LoginAccountRespose> getLiveData() {
        return liveData;
    }

    public void loginAPI(String pass, String email) {
        if (checkIsEmpty(pass, email)) {
            loginRepository.loginAPI(pass, email, liveData);
        }
    }

    public boolean checkIsEmpty(String pass, String username) {
        if (TextUtils.isEmpty(username)) {
            showToast("Username is empty!");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            showToast("Invalid email format!");
            return false;
        } else if (TextUtils.isEmpty(pass)) {
            showToast("Password is empty!");
            return false;
        }

        // If all checks pass, return true
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show();
    }

    public void checkSavePass(Boolean isSavePass) {
        editor.putBoolean(IS_SAVE_PASS, isSavePass).commit();
    }
}
