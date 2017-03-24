package com.acer.attendanceapp.Models;

import com.acer.attendanceapp.StudentSide.ListItem;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class basicNotificationModel implements ListItem {

    private String mSubjectName;
    private String mSchedule;
    private String mVenue;
    private String key;

    public basicNotificationModel(String mSubjectName, String mSchedule, String mVenue, String key) {
        this.mSubjectName = mSubjectName;
        this.mSchedule = mSchedule;
        this.mVenue = mVenue;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getmSubjectName() {
        return mSubjectName;
    }

    public void setmSubjectName(String mSubjectName) {
        this.mSubjectName = mSubjectName;
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

    @Override
    public int getListItemType() {
        return ListItem.BASIC;
    }
}
