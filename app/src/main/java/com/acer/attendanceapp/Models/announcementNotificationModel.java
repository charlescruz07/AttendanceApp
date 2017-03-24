package com.acer.attendanceapp.Models;

import com.acer.attendanceapp.StudentSide.ListItem;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class announcementNotificationModel implements ListItem {

    private String mSubjectName;

    public announcementNotificationModel(String mSubjectName) {
        this.mSubjectName = mSubjectName;
    }

    public String getmSubjectName() {
        return mSubjectName;
    }

    public void setmSubjectName(String mSubjectName) {
        this.mSubjectName = mSubjectName;
    }

    @Override
    public int getListItemType() {
        return ListItem.ANNOUNCEMENT;
    }
}
