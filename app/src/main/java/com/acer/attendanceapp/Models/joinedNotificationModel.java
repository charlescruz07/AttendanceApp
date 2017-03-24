package com.acer.attendanceapp.Models;

import com.acer.attendanceapp.StudentSide.ListItem;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class joinedNotificationModel implements ListItem {

    private String subjectName;

    public joinedNotificationModel(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public int getListItemType() {
        return ListItem.JOINED;
    }
}
