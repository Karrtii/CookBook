package com.example.cookbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cookbook.repositories.AuthenticationRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private AuthenticationRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = AuthenticationRepository.getInstance();
    }

    public void logOut()
    {
        repository.logOut();
    }

    public MutableLiveData<Boolean> getLogoutData()
    {
        return repository.getLoggedOutData();
    }
}
