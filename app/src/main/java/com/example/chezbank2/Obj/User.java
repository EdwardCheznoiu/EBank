package com.example.chezbank2.Obj;

public class User {
    private final String userFname;
    private final String userLname;
    private final String userEmail;
    private final String userPwd;
    private final String userAge;
    private String cnp;
    private String cardNr;
    private int userMoney;

    public User(String userFname, String userLname, String userEmail, String userPwd, String userAge, String cnp){
        this.userFname = userFname;
        this.userLname = userLname;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userAge = userAge;
        this.cnp = cnp;
        this.userMoney = 50;
        this.cardNr = Utilities.GenerateRandomCard();
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserPwd(){
        return userPwd;
    }

    public String getUserFname() {
        return userFname;
    }
    public String getUserLname() {
        return userLname;
    }
    public String getUserAge(){
        return userAge;
    }

    public String getUserCnp(){
        return cnp;
    }

    public String getUserCardNr(){
        return cardNr;
    }

    public int getUserMoney(){
        return userMoney;
    }
}
