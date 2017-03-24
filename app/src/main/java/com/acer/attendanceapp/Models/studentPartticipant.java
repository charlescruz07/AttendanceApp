package com.acer.attendanceapp.Models;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class studentPartticipant {

    private String studentName;
    private String time;

    public studentPartticipant() {
    }

    public studentPartticipant(String studentName, String time) {
        this.studentName = studentName;
        this.time = time;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
