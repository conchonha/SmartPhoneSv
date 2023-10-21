package edu.abcd.smartphone.presentation;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.domain.repositories.LoginRepository;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    @Inject
    public DataServiceClient dataServiceClient;

    MutableLiveData<LoginAccountRespose> liveData;
    @Inject
    public LoginViewModel(){
        liveData = new MutableLiveData();
    }

    public MutableLiveData<LoginAccountRespose> getLiveData() {
        return liveData;
    }

    public void loginAPI(){
        LoginRepository loginRepository = new LoginRepository(dataServiceClient);
        loginRepository.loginAPI("123456","xuanqn01@gmail.com", liveData);
    }
}
