package com.example.chezbank2.LoginSystem.Obj;
import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.chezbank2.LoginSystem.Activities.LoginActivity;
import com.example.chezbank2.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtilities {
    private static final FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private static final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://chezbank2-default-rtdb.europe-west1.firebasedatabase.app/");

    private static final FirebaseFirestore db = FirebaseFirestore.getInstance(mDatabase.getApp());


    public FirebaseAuth getFirebaseAuth(){
        return this.fAuth;
    }

    public FirebaseDatabase getFirebaseData(){
        return this.mDatabase;
    }

    public FirebaseFirestore getDb(){
        return this.db;
    }
}
