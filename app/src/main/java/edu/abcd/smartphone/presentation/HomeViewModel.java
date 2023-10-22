package edu.abcd.smartphone.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.response.CategoryRespose;
import edu.abcd.smartphone.data_source.remote.response.ProductRespose;
import edu.abcd.smartphone.domain.repositories.HomeRepository;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    @Inject
    public DataServiceClient dataServiceClient;

    MutableLiveData<List<CategoryRespose>> liveDataCategory;
    MutableLiveData<List<ProductRespose>> liveDataProduct;
    @Inject
    public HomeViewModel(){
        liveDataCategory = new MutableLiveData();
        liveDataProduct = new MutableLiveData<>();
    }

    public MutableLiveData<List<CategoryRespose>> getLiveDataCategory() {
        return liveDataCategory;
    }

    public MutableLiveData<List<ProductRespose>> getLiveDataProduct(){return liveDataProduct;}

    public void categoryAPI(){
        HomeRepository homeRepository = new HomeRepository(dataServiceClient);
        homeRepository.categoryAPI(liveDataCategory);
    }

    public void productAPI(){
        HomeRepository homeRepository = new HomeRepository(dataServiceClient);
        homeRepository.productAPI(liveDataProduct);
    }
}
