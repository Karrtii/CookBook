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
import com.example.cookbook.viewmodel.RegisterViewModel;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button registerButton;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);
        registerButton = findViewById(R.id.registerButton);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerViewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null)
                {
                    Intent alreadyLogged = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(alreadyLogged);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerViewModel.register(email.getText().toString().trim(),password.getText().toString().trim());
            }
        });
    }

    public void ToLogin(View view)
    {
        Intent toLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(toLogin);
    }

    @Override
    protected void onResume() {
        super.onResume();
        email.setText("");
        password.setText("");
    }
}