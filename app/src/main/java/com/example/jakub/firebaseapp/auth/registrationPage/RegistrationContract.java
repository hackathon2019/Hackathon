package com.example.jakub.firebaseapp.auth.registrationPage;

import com.example.jakub.firebaseapp.auth.AuthActivity;

import dagger.Binds;
import dagger.Module;

public interface RegistrationContract {

    interface View {

        AuthActivity getAuthActivity();
    }

    interface Presenter {
        void attachView(View view);

        void createNewUser(String email, String password);

        void detachView();
    }

    @Module()
    abstract class RegistrationModule {

        @Binds
        public abstract RegistrationContract.Presenter provideTrainingPresenter (RegistrationPresenter trainingPresenter);
    }
}
