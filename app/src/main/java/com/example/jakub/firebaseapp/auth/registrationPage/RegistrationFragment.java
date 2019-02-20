package com.example.jakub.firebaseapp.auth.registrationPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jakub.firebaseapp.MyApp;
import com.example.jakub.firebaseapp.R;
import com.example.jakub.firebaseapp.auth.AuthActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegistrationFragment extends Fragment implements RegistrationContract.View {

    public static final String TAG = RegistrationFragment.class.getSimpleName();

    @Inject
    RegistrationContract.Presenter presenter;

    private Unbinder unbinder;

    @BindView(R.id.emailRegistrationTextView)
    EditText emailTextView;

  @BindView(R.id.passwordRegistrationTextView)
    EditText passwordTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_fragment, container, false);
        presenter.attachView(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.registrationButton)
    public void registerUser(){
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        presenter.createNewUser(email,password);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public AuthActivity getAuthActivity() {
        return (AuthActivity) getActivity();
    }
}
