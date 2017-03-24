package com.acer.attendanceapp.Models;

import com.acer.attendanceapp.StudentSide.ListItem;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class decisionNotificationModel implements ListItem {

    private String classmateName;
    private boolean willAbsent;
    private String subjectName;

    public decisionNotificationModel(String classmateName, boolean willAbsent, String subjectName) {
        this.classmateName = classmateName;
        this.willAbsent = willAbsent;
        this.subjectName = subjectName;
    }

    public String getClassmateName() {
        return classmateName;
    }

    public void setClassmateName(String classmateName) {
        this.classmateName = classmateName;
    }

    public boolean isWillAbsent() {
        return willAbsent;
    }

    public void setWillAbsent(boolean willAbsent) {
        this.willAbsent = willAbsent;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public int getListItemType() {
        return ListItem.DECISION;
    }
}
