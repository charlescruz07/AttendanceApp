package com.acer.attendanceapp.Models;

import android.net.Uri;

/**
 * Created by Acer on 21/03/2017.
 */

public class ClassModel {
    private String classPic;
    private String schoolName;
    private String subjectName;
    private String day;
    private String time;
    private String room;
    private String classKey;

    public ClassModel() {

    }

    public String getClassPic() {
        return classPic;
    }

    public void setClassPic(String classPic) {
        this.classPic = classPic;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getClassKey() {
        return classKey;
    }

    public void setClassKey(String classKey) {
        this.classKey = classKey;
    }
}
