package com.example.cookbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cookbook.R;
import com.example.cookbook.viewmodel.LoginViewModel;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText email;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        loginButton = findViewById(R.id.loginButton);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null)
                {
                    Intent alreadyLogged = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(alreadyLogged);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(email.getText().toString().trim(),password.getText().toString().trim());
            }
        });
    }

    public void ToRegister(View view)
    {
        Intent toRegister = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(toRegister);
    }

    @Override
    protected void onResume() {
        super.onResume();
        email.setText("");
        password.setText("");
    }
}