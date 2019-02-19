package com.example.jakub.firebaseapp;

import android.app.Application;

import com.example.jakub.firebaseapp.app.AppComponent;
import com.example.jakub.firebaseapp.app.AppModule;
import com.example.jakub.firebaseapp.app.DaggerAppComponent;

public class MyApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
