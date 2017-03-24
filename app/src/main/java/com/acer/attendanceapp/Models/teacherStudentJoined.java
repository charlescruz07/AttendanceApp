package com.acer.attendanceapp.Models;

import com.acer.attendanceapp.TeacherSide.notificationType;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class teacherStudentJoined implements notificationType {

    private String mStudentName;
    private String mSubjectName;

    public teacherStudentJoined(String mStudentName, String mSubjectName) {
        this.mStudentName = mStudentName;
        this.mSubjectName = mSubjectName;
    }

    public String getmStudentName() {
        return mStudentName;
    }

    public void setmStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }

    public String getmSubjectName() {
        return mSubjectName;
    }

    public void setmSubjectName(String mSubjectName) {
        this.mSubjectName = mSubjectName;
    }

    @Override
    public int getNotificationType() {
        return notificationType.JOINED;
    }
}
