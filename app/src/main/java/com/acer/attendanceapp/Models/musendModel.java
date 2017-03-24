package com.acer.attendanceapp.Models;

import com.acer.attendanceapp.StudentSide.studentReplies;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class musendModel implements studentReplies {

    private String message;

    public musendModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getStudentReplyType() {
        return studentReplies.MUSEND;
    }
}
