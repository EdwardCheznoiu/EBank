package com.example.chezbank2.LoginSystem.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chezbank2.MainActivity;
import com.example.chezbank2.LoginSystem.Obj.FirebaseUtilities;
import com.example.chezbank2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private TextView userInputEmailLogin;
    private TextView userInputPwdLogin;
    private Button loginButton;
    private Button registerButton;
    private FirebaseUtilities fUtilities = new FirebaseUtilities();
    private FirebaseDatabase database;
    private DatabaseReference refUsersFth;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userInputEmailLogin = findViewById(R.id.loginInputEmail);
        userInputPwdLogin   = findViewById(R.id.loginInputPassword);
        loginButton = findViewById(R.id.loginBtn);
        registerButton = findViewById(R.id.signUpBtn);
        fAuth = FirebaseAuth.getInstance();

        if(fUtilities.getFirebaseAuth().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        else{
            setRegisterBtn();
            setLoginBtn();
        }
    }

    private void setRegisterBtn(){
        registerButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    private void setLoginBtn(){
        loginButton.setOnClickListener(v -> {
            if(validateFields()) {
                if(fUtilities.tryLogIn(userInputEmailLogin.getText().toString(), userInputPwdLogin.getText().toString())){
                    Toast.makeText(LoginActivity.this, "You have been loged in!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Creditals may be incorect!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validateFields(){
        if (userInputEmailLogin.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this, "Email is empty!", Toast.LENGTH_SHORT).show();
            userInputEmailLogin.setError("Empty Field");
            return false;
        }
        else if(userInputPwdLogin.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this, "Password is empty!", Toast.LENGTH_SHORT).show();
            userInputPwdLogin.setError("Empty Field");
            return false;
        }
        return true;
    }
}