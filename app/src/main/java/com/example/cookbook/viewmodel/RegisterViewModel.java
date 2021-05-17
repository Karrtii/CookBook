package com.example.cookbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cookbook.repositories.AuthenticationRepository;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends AndroidViewModel {

    private AuthenticationRepository repository;
    private MutableLiveData<FirebaseUser> userData;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = AuthenticationRepository.getInstance();
        userData = repository.getUserData();
    }

    public void register(String email, String password)
    {
        repository.register(email, password);
    }

    public MutableLiveData<FirebaseUser> getUserData(){return  userData;}
}
