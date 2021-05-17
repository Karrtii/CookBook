package com.example.cookbook.repositories;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationRepository {
    private static AuthenticationRepository instance;
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> loggedOutData;

    public AuthenticationRepository() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        userData = new MutableLiveData<>();
        loggedOutData = new MutableLiveData<>();

        if (firebaseAuth.getCurrentUser() != null)
        {
            userData.postValue(firebaseAuth.getCurrentUser());
            loggedOutData.postValue(false);
        }
    }

    public static synchronized AuthenticationRepository getInstance()
    {
        if(instance == null)
        {
            instance = new AuthenticationRepository();
        }
        return instance;
    }

    public void login(String email, String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    userData.postValue(firebaseAuth.getCurrentUser());
                    loggedOutData.postValue(false);
                }
                else
                    {
                        Log.i("TAG", task.getException().getMessage());
                    }
            }
        });
    }

    public void register(String email, String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    loggedOutData.postValue(false);
                }
                else
                    {
                        Log.i("TAG", task.getException().getMessage());
                    }
            }
        });
    }

    public void logOut()
    {
        firebaseAuth.signOut();
        loggedOutData.setValue(true);
    }

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedOutData() {
        return loggedOutData;
    }
}
