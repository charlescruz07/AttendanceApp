package com.acer.attendanceapp.Models;

import com.acer.attendanceapp.StudentSide.ListItem;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class basicNotificationModel implements ListItem {

    private String mSubjectName;
    private String mSchedule;
    private String mVenue;

    public basicNotificationModel(String mSubjectName, String mSchedule, String mVenue) {
        this.mSubjectName = mSubjectName;
        this.mSchedule = mSchedule;
        this.mVenue = mVenue;
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
