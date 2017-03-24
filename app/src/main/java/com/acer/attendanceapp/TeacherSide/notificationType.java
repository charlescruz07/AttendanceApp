package com.acer.attendanceapp.TeacherSide;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public interface notificationType {

    int VIEW = 1;
    int JOINED = 2;
    int DROP = 3;

    int getNotificationType();
}
