package com.example.jakub.firebaseapp.auth.loginPage;

import android.support.annotation.NonNull;

import com.example.jakub.firebaseapp.util.Logger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class LoginPresenter implements LoginContract.Presenter {

    public static final String TAG = LoginPresenter.class.getSimpleName();
    @Inject
    public Logger logger;
    private LoginContract.View view;
    private boolean isSigned;
    private FirebaseAuth mAuth;

    @Inject
    public LoginPresenter() {
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
        isSigned = this.checkISigned();
    }

    private boolean checkISigned() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) return false;
        else return true;
    }

    @Override
    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(view.getAuthActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            logger.log(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            view.getAuthActivity().startNewActivity();

                        } else {
                            // If sign in fails, display a message to the user.
                            logger.log(TAG, "signInWithEmail:failure: " + task.getException());
                        }

                    }
                });
    }

    @Override
    public void detachView() {
        view = null;
    }
}
