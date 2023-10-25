package edu.abcd.smartphone.di;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UtilModule {
    @Provides
    @Singleton
    public SharedPreferences sharedPreferences(Application application){
        return application.getSharedPreferences("SharedPreferences",MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public SharedPreferences.Editor editor(SharedPreferences sharedPreferences){
        return sharedPreferences.edit();
    }
}
