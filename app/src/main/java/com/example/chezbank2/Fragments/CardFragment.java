package com.example.chezbank2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chezbank2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class CardFragment extends Fragment {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://chezbank2-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private TextView cardNumber;
    private TextView cardBalance;
    private TextView cardName;

    public static CardFragment newInstance(String param1, String param2) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        cardNumber = view.findViewById(R.id.cardNr);
        cardBalance = view.findViewById(R.id.cardBalance);
        cardName = view.findViewById(R.id.cardName);
        setBalance();
        setCardNumber();
        setFname();
        setLname();
        return view;
    }

    private void setBalance(){
        mDatabase.child(fAuth.getUid()).child("userMoney").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    cardBalance.setText(task.getResult().getValue().toString() + " RON");
                }
                else{
                    cardBalance.setText("No card number");
                }
            }
        });
    }

    private void setCardNumber(){
        mDatabase.child(fAuth.getUid()).child("userCardNr").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    cardNumber.setText(task.getResult().getValue().toString());
                }
                else{
                    cardNumber.setText("No card balance");
                }
            }
        });
    }

    private void setFname(){
        mDatabase.child(fAuth.getUid()).child("userFname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    cardName.setText(task.getResult().getValue().toString());
                }
                else{
                    cardName.setText("No name");
                }
            }
        });
    }

    private void setLname(){
        mDatabase.child(fAuth.getUid()).child("userLname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    cardName.setText(cardName.getText().toString() + " " + task.getResult().getValue().toString());
                }
                else{
                    cardName.setText("No name");
                }
            }
        });
    }
}