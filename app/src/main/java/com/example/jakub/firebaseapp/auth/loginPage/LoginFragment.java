package com.example.jakub.firebaseapp.auth.loginPage;

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

public class LoginFragment extends Fragment implements LoginContract.View {

    public static final String TAG = LoginFragment.class.getSimpleName();

    @Inject
    LoginContract.Presenter presenter;

    private Unbinder unbinder;

    @BindView(R.id.emailLoginTextView)
    EditText emailTextView;

  @BindView(R.id.passwordLoginTextView)
    EditText passwordTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        presenter.attachView(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.loginButton)
    public void loginUser(){
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        presenter.loginUser(email,password);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public AuthActivity getAuthActivity() {
        return (AuthActivity) getActivity();
    }
}
