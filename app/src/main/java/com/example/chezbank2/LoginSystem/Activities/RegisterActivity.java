package com.example.chezbank2.LoginSystem.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chezbank2.LoginSystem.Obj.Register;
import com.example.chezbank2.MainActivity;
import com.example.chezbank2.Obj.User;
import com.example.chezbank2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private TextView fname, lname, email, password, passwordRpt, age, cnp;
    private Button signup;
    private ArrayList<TextView> userViews = new ArrayList<>();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        viewsInit();
        eventsInit();
    }

    private void viewsInit(){
        fname = findViewById(R.id.registerFname);
        lname = findViewById(R.id.registerLname);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        passwordRpt = findViewById(R.id.registerPasswordRpt);
        age = findViewById(R.id.registerAge);
        cnp = findViewById(R.id.registerCnp);
        signup = findViewById(R.id.signUpBtnReg2);
    }

    private void eventsInit(){
        signup.setOnClickListener(v -> {
            User user = new User(fname.getText().toString(),
                    lname.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString(),
                    age.getText().toString(),
                    cnp.getText().toString());
            Register register = new Register(user, RegisterActivity.this, userViews);
            register.register();
        });
    }

    private void assignTextViewToArray(){
        userViews.add(fname);
        userViews.add(lname);
        userViews.add(email);
        userViews.add(password);
        userViews.add(age);
        userViews.add(cnp);
    }
}