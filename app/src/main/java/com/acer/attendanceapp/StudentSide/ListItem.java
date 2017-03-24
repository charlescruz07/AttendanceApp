package com.acer.attendanceapp.StudentSide;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public interface ListItem {

    int BASIC = 1;
    int ANNOUNCEMENT = 2;
    int DECISION = 3;
    int TOBE_DROPPED = 4;
    int DROPPED = 5;
    int JOINED = 6;

    int getListItemType();

}
