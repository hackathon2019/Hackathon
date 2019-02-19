package com.example.jakub.firebaseapp.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.jakub.firebaseapp.MyApp;
import com.example.jakub.firebaseapp.R;
import com.example.jakub.firebaseapp.auth.loginPage.LoginFragment;
import com.example.jakub.firebaseapp.auth.registrationPage.RegistrationFragment;
import com.example.jakub.firebaseapp.dalejjjj.DalejActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class AuthActivity extends AppCompatActivity {

    public static final String TAG = AuthActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private String currentFragmentTAG = "";

    @Inject
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ((MyApp) getApplication()).getAppComponent().inject(this);
        mAuth = FirebaseAuth.getInstance();
        if(this.checkISigned())this.replaceFragment(new LoginFragment(),LoginFragment.TAG);
        else this.replaceFragment(new RegistrationFragment(),RegistrationFragment.TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public <T extends Fragment> void replaceFragment(T fragment, String TAG) {
        this.currentFragmentTAG = TAG;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentApp, fragment, TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

    private boolean checkISigned() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) return false;
        else return true;
    }

    public void startNewActivity(){
        Intent myIntent = new Intent(AuthActivity.this, DalejActivity.class);
        AuthActivity.this.startActivity(myIntent);
    }
}
