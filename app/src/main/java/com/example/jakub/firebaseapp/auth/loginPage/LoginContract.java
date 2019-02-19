package com.example.jakub.firebaseapp.auth.loginPage;

import com.example.jakub.firebaseapp.auth.AuthActivity;

import dagger.Binds;
import dagger.Module;

public interface LoginContract {

    interface View {

        AuthActivity getAuthActivity();
    }

    interface Presenter {
        void attachView(View view);

        void loginUser(String email, String password);

        void detachView();
    }

    @Module()
    abstract class LoginModule {

        @Binds
        public abstract LoginContract.Presenter provideTrainingPresenter (LoginPresenter trainingPresenter);
    }
}
