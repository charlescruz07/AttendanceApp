package com.acer.attendanceapp.Models;

import com.acer.attendanceapp.StudentSide.studentReplies;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class mudawatModel implements studentReplies {

    private int picture;
    private String message;

    public mudawatModel(int picture, String message) {
        this.picture = picture;
        this.message = message;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getStudentReplyType() {
        return studentReplies.MUDAWAT;
    }
}
