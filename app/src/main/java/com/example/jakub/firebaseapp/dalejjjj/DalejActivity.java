package com.example.jakub.firebaseapp.dalejjjj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jakub.firebaseapp.MyApp;
import com.example.jakub.firebaseapp.R;
import com.example.jakub.firebaseapp.util.Logger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

public class DalejActivity extends AppCompatActivity {

    public static final String TAG = DalejActivity.class.getSimpleName();
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Inject
    Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dalej);
        ((MyApp) getApplication()).getAppComponent().inject(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("place");
        myRef.setValue("Hello, World!");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                logger.log(TAG, "Value is: " + value);
                Toast.makeText(getApplicationContext(),"Value is: " + value,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                logger.log(TAG, "Failed to read value: "+ error.toException());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
