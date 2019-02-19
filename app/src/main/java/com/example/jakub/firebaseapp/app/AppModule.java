package com.example.jakub.firebaseapp.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final String APP_NAME = "application";

    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedPreferences.Editor provideSharedPreferencesEditor(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }

    @Singleton
    @Provides
    Vibrator getVibration(Context context) {
        return (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }


}
