package com.example.jakub.firebaseapp.app;

import com.example.jakub.firebaseapp.auth.AuthActivity;
import com.example.jakub.firebaseapp.auth.loginPage.LoginContract;
import com.example.jakub.firebaseapp.auth.loginPage.LoginFragment;
import com.example.jakub.firebaseapp.auth.registrationPage.RegistrationContract;
import com.example.jakub.firebaseapp.auth.registrationPage.RegistrationFragment;
import com.example.jakub.firebaseapp.dalejjjj.DalejActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, LoginContract.LoginModule.class, RegistrationContract.RegistrationModule.class})
public interface AppComponent {
    void inject(AuthActivity authActivity);
    void inject(DalejActivity dalejActivity);
    void inject(LoginFragment loginFragment);
    void inject(RegistrationFragment registrationFragment);
}
