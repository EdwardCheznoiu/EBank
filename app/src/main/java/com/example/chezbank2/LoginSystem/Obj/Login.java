package com.example.chezbank2.LoginSystem.Obj;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.chezbank2.MainActivity;
import com.example.chezbank2.Obj.ActivityContext;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Login {
    private String email;
    private String password;
    private Context context;
    private FirebaseUtilities firebaseUtilities = new FirebaseUtilities();

    public Login(String email, String password, Context context){
        this.email = email;
        this.password = password;
        this.context = context;
    }

    public Login(){}

    public void login(){
        firebaseUtilities.getFirebaseAuth().signInWithEmailAndPassword(this.email, this.password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "You have been loged in!", Toast.LENGTH_SHORT).show();
                ActivityContext.navigate(context, MainActivity.class);
            } else
                Toast.makeText(context, "Creditals may be incorect!", Toast.LENGTH_SHORT).show();
        });
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public Context getContext(){
        return context;
    }

    public FirebaseUtilities getFirebaseUtilities(){
        return firebaseUtilities;
    }
}
