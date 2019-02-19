package com.example.jakub.firebaseapp.auth.registrationPage;

import android.support.annotation.NonNull;

import com.example.jakub.firebaseapp.util.Logger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    public static final String TAG = RegistrationPresenter.class.getSimpleName();
    @Inject
    public Logger logger;
    private RegistrationContract.View view;
    private boolean isSigned;
    private FirebaseAuth mAuth;

    @Inject
    public RegistrationPresenter() {
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void attachView(RegistrationContract.View view) {
        this.view = view;
        isSigned = this.checkISigned();
    }

    private boolean checkISigned() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) return false;
        else return true;
    }

    @Override
    public void createNewUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(view.getAuthActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            logger.log(TAG, "createUserWithEmail:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            logger.log(TAG, "createUserWithEmail:failure: "+ task.getException());

                        }
                    }
                });
    }

    @Override
    public void detachView() {
        view = null;
    }
}
