package com.example.chezbank2.LoginSystem.Obj;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.chezbank2.LoginSystem.Activities.LoginActivity;
import com.example.chezbank2.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Login {
    private FirebaseUtilities firebaseUtilities = new FirebaseUtilities();

    public void login(String email, String password){
        firebaseUtilities.getFirebaseAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "You have been loged in!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Creditals may be incorect!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
