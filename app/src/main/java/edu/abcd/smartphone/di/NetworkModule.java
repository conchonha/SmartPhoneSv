package edu.abcd.smartphone.di;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
 public class NetworkModule {
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
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public DataServiceClient providerDataServiceClient(Retrofit retrofit){
        return retrofit.create(DataServiceClient.class);
    }
}