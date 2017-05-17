package com.example.administrator.project207;

/**
 * Created by Administrator on 2017-05-15.
 */

public class History {


    int id;
    int usernumber;
    int status ;
    String Purpose ;
    String Time ;

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(int usernumber) {
        this.usernumber = usernumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public History(int id, int usernumber, int status) {
        this.id = id;
        this.usernumber = usernumber;
        this.status = status;

    }
}
