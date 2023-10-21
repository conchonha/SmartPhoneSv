package edu.abcd.smartphone.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
 public class NetworkModule {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    @Provides
    @Singleton
    public OkHttpClient providerOkhttpClient(){
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    public Retrofit providerRetrofit(OkHttpClient okHttpClient){
        return new Retrofit
                .Builder()
                .baseUrl("http://192.168.1.9:8112/core/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public DataServiceClient providerDataServiceClient(Retrofit retrofit){
        return retrofit.create(DataServiceClient.class);
    }
}