package com.example.chezbank2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chezbank2.LoginSystem.Activities.LoginActivity;
import com.example.chezbank2.Obj.User;
import com.example.chezbank2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class TransferFragment extends Fragment {

    private TextView reciverName;
    private TextView emailAddress;
    private TextView value;
    private int thisUserMoney = 0;
    private int otherUserMoney = 0;
    private Button sendBtn;
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://chezbank2-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");
    private TextView testEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer, container, false);
        reciverName = view.findViewById(R.id.reciverName);
        emailAddress = view.findViewById(R.id.emailAddress);
        value = view.findViewById(R.id.value);
        sendBtn = view.findViewById(R.id.sendMoneyBtn);
        testEmail = view.findViewById(R.id.testEmail);
        sendMoney();
        return view;
    }


    private void sendMoney(){
        sendBtn.setOnClickListener(v -> {
            if(validateFields()){
                updateDataBase();
            }
        });
    }

    private void updateDataBase(){
        Query userByEmail = mDatabase.orderByChild("userEmail").equalTo(emailAddress.getText().toString());
        userByEmail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String uid = child.getKey();
                    if(getThisUserMoney() >= Integer.parseInt(value.getText().toString())){
                        getOtherUserMoney(uid);
                        updateCurentUser();
                        updateOtherUser(uid.toString());
                        clearInputs();
                        Toast.makeText(getContext(), "Money have been send!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(), "You don't have enough money!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                testEmail.setText("Error: Task was canceled");
            }
        });
    }

    private int getThisUserMoney(){
        mDatabase.child(fAuth.getUid()).child("userMoney").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                thisUserMoney = Integer.parseInt(task.getResult().getValue().toString());
            }
            else{
                testEmail.setText("~isHavingMoney.toString()");
                thisUserMoney = 0;
            }
        });
        return thisUserMoney;
    }

    private int getOtherUserMoney(String uid){
        mDatabase.child(uid.toString()).child("userMoney").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                otherUserMoney = Integer.parseInt(task.getResult().getValue().toString());
            }
            else{
                testEmail.setText("~isHavingMoney.toString()");
                otherUserMoney = 0;
            }
        });
        return otherUserMoney;
    }


    private void updateCurentUser(){
        mDatabase.child(fAuth.getUid()).child("userMoney").setValue(String.valueOf(thisUserMoney - Integer.parseInt(value.getText().toString())));
    }

    private void updateOtherUser(String uid){
        mDatabase.child(uid).child("userMoney").setValue(String.valueOf(otherUserMoney + Integer.parseInt(value.getText().toString())));
    }

    private void clearInputs(){
        reciverName.setText("");
        emailAddress.setText("");
        value.setText("");
    }

    private boolean validateFields(){
        if(reciverName.getText().toString().isEmpty()){
            reciverName.setError("Empty fields!");
            Toast.makeText(getContext(), "Empty Reciver Name!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(emailAddress.getText().toString().isEmpty()){
            emailAddress.setError("Empty fields!");
            Toast.makeText(getContext(), "Empty Card Number!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(value.getText().toString().isEmpty()){
            value.setError("Empty fields!");
            Toast.makeText(getContext(), "Empty Value!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}