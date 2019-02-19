package com.example.jakub.firebaseapp.util;

import android.location.Location;
import android.util.Log;

import javax.inject.Inject;

public class Logger {

    @Inject
    public Logger() {
    }

    public void log(String TAG, String msg){
        Log.i(TAG,msg);
    }

    public void log(String TAG, int msg){
        Log.i(TAG,String.valueOf(msg));
    }

    public void log(String TAG, float msg){
        Log.i(TAG,String.valueOf(msg));
    }

    public void log(String TAG, double msg){
        Log.i(TAG,String.valueOf(msg));
    }

    public void log(String TAG, boolean msg){
        Log.i(TAG,String.valueOf(msg));
    }

    public void log(String TAG, Location location){ Log.i(TAG,"Latitude: "+ String.valueOf(location.getLatitude())+" , longitude: "+ String.valueOf(location.getLongitude()));}
}
