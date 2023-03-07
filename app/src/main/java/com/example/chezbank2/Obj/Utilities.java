package com.example.chezbank2.Obj;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Utilities {
    public static String GenerateRandomCard(){
        Random random = new Random();
        long nr = Math.round(random.nextFloat() * Math.pow(10, 12));
        return String.valueOf(nr);
    }
}
