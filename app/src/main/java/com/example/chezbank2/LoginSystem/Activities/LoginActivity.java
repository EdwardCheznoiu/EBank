package com.example.chezbank2.LoginSystem.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.chezbank2.LoginSystem.Obj.Login;
import com.example.chezbank2.MainActivity;
import com.example.chezbank2.R;

public class LoginActivity extends AppCompatActivity {

    private TextView userInputEmailLogin;
    private TextView userInputPwdLogin;
    private Button loginButton;
    private Button registerButton;
    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userInputEmailLogin = findViewById(R.id.loginInputEmail);
        userInputPwdLogin   = findViewById(R.id.loginInputPassword);
        loginButton = findViewById(R.id.loginBtn);
        registerButton = findViewById(R.id.signUpBtn);

        login = new Login();

        if(login.getFirebaseUtilities().getFirebaseAuth().getCurrentUser() != null){
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
                login = new Login(userInputEmailLogin.getText().toString(), userInputPwdLogin.getText().toString(), LoginActivity.this);
                login.login();
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