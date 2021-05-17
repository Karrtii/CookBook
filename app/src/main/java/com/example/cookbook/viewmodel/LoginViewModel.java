package com.example.cookbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cookbook.repositories.AuthenticationRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private AuthenticationRepository repository;
    private MutableLiveData<FirebaseUser> userData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = AuthenticationRepository.getInstance();
        userData = repository.getUserData();
    }

    public void login(String email, String password)
    {
        repository.login(email, password);
    }

    public MutableLiveData<FirebaseUser> getUserData(){ return  userData;}
}
