package com.acer.attendanceapp.Models;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class studentMessagesModel {

    private int mStudentPic;
    private String mStudentName;

    public studentMessagesModel(int mStudentPic, String mStudentName) {
        this.mStudentPic = mStudentPic;
        this.mStudentName = mStudentName;
    }

    public int getmStudentPic() {
        return mStudentPic;
    }

    public void setmStudentPic(int mStudentPic) {
        this.mStudentPic = mStudentPic;
    }

    public String getmStudentName() {
        return mStudentName;
    }

    public void setmStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }
}
