package com.example.administrator.project207;

/**
 * Created by Administrator on 2017-04-13.
 */

public class User {

    String userID ;
    String userName;
    String userOrder;
    String bookDate;
    String Purpose;


    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String Purpose) {
        this.bookDate = Purpose;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(String userOrder) {
        this.userOrder = userOrder;
    }




    public User(String userID, String userName, String Purpose, String bookDate, String userOrder) {
        this.userID = userID;
        this.userName = userName;
        this.bookDate = bookDate;
        this.userOrder = userOrder;
        this.Purpose = Purpose;
    }





}
