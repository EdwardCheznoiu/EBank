package com.example.chezbank2.Obj;

import android.content.Context;
import android.content.Intent;

public class ActivityContext {
    public static void navigate(Context context, Class<?> nameOfClass){
        Intent intent = new Intent(context, nameOfClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
