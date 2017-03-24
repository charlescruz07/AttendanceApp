package com.acer.attendanceapp.Models;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentClassesModel {

    private String mSubject;
    private String mSchedule;
    private String mVenue;
    private String mProfessor;
    private String mProfPic;

    public studentClassesModel() {

    }

    public int getNumOfAbsences() {
        return numOfAbsences;
    }

    public void setNumOfAbsences(int numOfAbsences) {
        this.numOfAbsences = numOfAbsences;
    }

    private int numOfAbsences;

    public studentClassesModel(String mSubject, String mSchedule, String mVenue, String mProfessor, String mProfPic, int numOfAbsences) {
        this.mSubject = mSubject;
        this.mSchedule = mSchedule;
        this.mVenue = mVenue;
        this.mProfessor = mProfessor;
        this.mProfPic = mProfPic;
        this.numOfAbsences = numOfAbsences;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmSchedule() {
        return mSchedule;
    }

    public void setmSchedule(String mSchedule) {
        this.mSchedule = mSchedule;
    }

    public String getmVenue() {
        return mVenue;
    }

    public void setmVenue(String mVenue) {
        this.mVenue = mVenue;
    }

    public String getmProfessor() {
        return mProfessor;
    }

    public void setmProfessor(String mProfessor) {
        this.mProfessor = mProfessor;
    }

    public String getmProfPic() {
        return mProfPic;
    }

    public void setmProfPic(String mProfPic) {
        this.mProfPic = mProfPic;
    }
}
