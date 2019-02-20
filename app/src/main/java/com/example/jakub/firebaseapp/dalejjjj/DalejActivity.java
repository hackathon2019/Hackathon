package com.example.jakub.firebaseapp.dalejjjj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.jakub.firebaseapp.MyApp;
import com.example.jakub.firebaseapp.R;
import com.example.jakub.firebaseapp.dao.FireBasePlaceHelper;
import com.example.jakub.firebaseapp.dao.Place;
import com.example.jakub.firebaseapp.util.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DalejActivity extends AppCompatActivity {

    public static final String TAG = DalejActivity.class.getSimpleName();


    @BindView(R.id.placeNameEditText)
    EditText placeNameEditText;

    @BindView(R.id.placeDescriptionText)
    EditText placeDescriptionText;
    @Inject
    Logger logger;
    @Inject
    FireBasePlaceHelper fireBasePlaceHelper;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dalej);
        ((MyApp) getApplication()).getAppComponent().inject(this);

        unbinder = ButterKnife.bind(this);

    }


    @OnClick(R.id.savePlaceButton)
    public void savePlaceToFirebase() {
        String placeName = placeNameEditText.getText().toString();
        String placeDescription = placeDescriptionText.getText().toString();
        Date currentTime = Calendar.getInstance().getTime();
        Place place = new Place(placeName, 10, 11,  placeDescription);

        fireBasePlaceHelper.savePlace(place);
    }

    @OnClick(R.id.readPlaceButton)
    public void readPlaceFromFireBase(){
        List<Place> placeList= fireBasePlaceHelper.getPlace();
        for(Place place : placeList){
            logger.log(TAG, place.getName()+" "+place.getDescription());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
