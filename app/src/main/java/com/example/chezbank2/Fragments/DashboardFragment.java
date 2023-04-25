package com.example.chezbank2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chezbank2.LoginSystem.Obj.FirebaseUtilities;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private FirebaseUtilities firebaseUtilities = new FirebaseUtilities();
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private TextView authUserName;
    private TextView authUserBalance;
    private ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ListView listView;
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
        listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        setGreetings();
        setBalance();
        setTransactionsHistory();
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

    private void setTransactionsHistory(){
        firebaseUtilities.getFirebaseData().getReference("transactions").orderByKey().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    int cnt = 0;
                    boolean canWrite = false;
                    String item;
                    for (DataSnapshot child : task.getResult().getChildren()) {
                        item = "";
                        for(DataSnapshot d : child.getChildren()){
                            if(cnt == 0){
                                if(d.getValue().toString().equals(firebaseUtilities.getFirebaseAuth().getUid())){
                                    canWrite = true;
                                }
                                else{
                                    canWrite = false;
                                }
                            }
                            if(canWrite && cnt > 0){
                                item += d.getValue().toString() + " ";
                            }
                            if(cnt == 1){
                                item += " lei - ";
                            }
                            cnt++;
                            if(cnt > 2 && canWrite){
                                listItems.add(item);
                                listView.setAdapter(adapter);
                            }
                            if(cnt > 2) cnt = 0;
                        }

                    }

                }
                else{

                }
            }
        });
    }
}