package com.acer.attendanceapp.Models;

/**
 * Created by Acer on 20/03/2017.
 */

public class Users {
    private String userName;
    private String userRole;
    private String userPic;

    public Users() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }
}
