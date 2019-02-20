package com.example.jakub.firebaseapp.dao;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FireBasePlaceHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference placeReference;
    private List<Place> placeList;

    long maxId;

    @Inject
    public FireBasePlaceHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        placeReference = firebaseDatabase.getReference().child("place");
        placeList = new ArrayList<>();
        placeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxId = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        placeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                placeList.clear();
                List<String> key = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    key.add(keyNode.getKey());
                    Place place = keyNode.getValue(Place.class);
                    placeList.add(place);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void savePlace(Place place){
        place.setId(maxId);
        placeReference.child(String.valueOf(maxId)).setValue(place);
    }

    public List<Place> getPlace(){
        return placeList;
    }

}
