package com.example.chezbank2.Fragments.Obj;

public class Transfer {
    private String currentUserId;
    private String otherUserName;
    private String moneySent;

    public Transfer(String currentUserId, String otherUserName, String moneySent){
        this.currentUserId = currentUserId;
        this.otherUserName = otherUserName;
        this.moneySent = moneySent;
    }

    public String getCurrentUserID(){
        return currentUserId;
    }

    public String getOtherUserName(){
        return otherUserName;
    }

    public String getMoneySent(){
        return moneySent;
    }
}
