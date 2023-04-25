package com.example.chezbank2.LoginSystem.Obj;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import com.example.chezbank2.LoginSystem.Activities.RegisterActivity;
import com.example.chezbank2.MainActivity;
import com.example.chezbank2.Obj.ActivityContext;
import com.example.chezbank2.Obj.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Register {
    private User user;

    private Context context;

    private FirebaseUtilities firebaseUtilities = new FirebaseUtilities();
    private ArrayList<TextView> usersViews = new ArrayList<TextView>();

    public Register(User user, Context context, ArrayList<TextView> usersViews){
        this.user = user;
        this.context = context;
        this.usersViews = usersViews;
    }

    public void register(){
        firebaseUtilities.getFirebaseAuth().createUserWithEmailAndPassword(user.getUserEmail(), user.getUserPwd()).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                firebaseUtilities.getFirebaseData().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                        .addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()){
                                Toast.makeText(context, "User created!", Toast.LENGTH_SHORT).show();
                                ActivityContext.navigate(context, MainActivity.class);
                            }
                            else {
                                Toast.makeText(context, "User was not created!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else{
            }
        });

    }

    private void validateFields(){
        if (user.getUserFname().isEmpty()) {
            Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show();
            usersViews.get(0).setError("Empty field");
        }
        else if(user.getUserLname().isEmpty()){
            Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show();
            usersViews.get(1).setError("Empty field");
        }
        else if(user.getUserEmail().isEmpty()) {
            Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show();
            usersViews.get(2).setError("Empty field");
        }
        else if(user.getUserPwd().isEmpty()){
            Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show();
            usersViews.get(3).setError("Empty field");
        }
        else if (user.getUserAge().isEmpty()) {
            Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show();
            usersViews.get(4).setError("Empty field");
        }
        else if(user.getUserAge().isEmpty()){
            Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show();
            usersViews.get(5).setError("Empty field");
        }
        else if(user.getUserCnp().isEmpty()){
            Toast.makeText(context, "Empty fields!", Toast.LENGTH_SHORT).show();
            usersViews.get(6).setError("Empty field");
        }
        else if (!user.getUserPwd().equals(user.getUserPwd())){
            Toast.makeText(context, "Passwords didn't match!", Toast.LENGTH_SHORT).show();
        }
        else if(user.getUserPwd().length() < 6){
            Toast.makeText(context, "Password is too short!", Toast.LENGTH_SHORT).show();
        }
    }

    public User getUser() {
        return user;
    }




}
