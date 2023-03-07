package com.example.chezbank2.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chezbank2.LoginSystem.Activities.LoginActivity;
import com.example.chezbank2.MainActivity;
import com.example.chezbank2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class DashboardFragment extends Fragment {

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private TextView authUserName;
    private TextView authUserBalance;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://chezbank2-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        authUserName = view.findViewById(R.id.authName);
        authUserBalance = view.findViewById(R.id.userBalance);
        setGreetings();
        setBalance();
        return view;
    }

    private void setGreetings(){
        mDatabase.child(fAuth.getUid()).child("userFname").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                authUserName.setText("Hello " + task.getResult().getValue().toString());
            }else{
                authUserName.setText("Hello @Noname");
            }
        });
    }

    private void setBalance(){
        mDatabase.child(fAuth.getUid()).child("userMoney").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                authUserBalance.setText(task.getResult().getValue().toString() + " RON");
            }else{
                authUserBalance.setText("@Nomoney");
            }
        });
    }

}