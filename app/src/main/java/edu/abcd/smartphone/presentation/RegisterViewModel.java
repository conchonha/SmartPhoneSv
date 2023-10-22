package edu.abcd.smartphone.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose;
import edu.abcd.smartphone.data_source.remote.response.RegisterAccountResponse;
import edu.abcd.smartphone.domain.repositories.LoginRepository;
import edu.abcd.smartphone.domain.repositories.RegisterRepository;

@HiltViewModel
public class RegisterViewModel extends ViewModel {
    @Inject
    public DataServiceClient dataServiceClient;

    MutableLiveData<RegisterAccountResponse> liveData;
    @Inject
    public RegisterViewModel(){
        liveData = new MutableLiveData();
    }

    public MutableLiveData<RegisterAccountResponse> getLiveData() {
        return liveData;
    }

    public void registerAPI(String email, String name, String pass){
        RegisterRepository registerRepository = new RegisterRepository(dataServiceClient);
        registerRepository.registerAPI(email, name, pass, liveData);
    }
}
