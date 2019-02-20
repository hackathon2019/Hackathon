package com.example.jakub.firebaseapp.dao;

import android.location.Location;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Place {

    @Getter
    @Setter
    long id;
    @Getter
    @Setter
    String name;
    @Getter
    @Setter
    double lat;
    @Getter
    @Setter
    double lon;
    @Getter
    @Setter
    String description;

    public Place(){

    }

    public Place(String name, double lat, double lon,  String description) {
        this.name = name;
        this.lat = lat;
        this.lon=lon;
        this.description = description;
    }

}
