package com.example.demo;

public class User {
    public String userID;
    public String userName;
    public String userUsername;

    public User(String userID, String userName, String userUsername) {
        this.userID = userID;
        this.userName = userName;
        this.userUsername = userUsername;
    }

    public User() {

    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;

    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}
