package com.acer.attendanceapp.TeacherSide;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acer.attendanceapp.Models.teacherStudentJoined;
import com.acer.attendanceapp.R;
import com.acer.attendanceapp.StudentSide.ListItem;
import com.acer.attendanceapp.StudentSide.StudentActivity;
import com.acer.attendanceapp.StudentSide.studentNotificationRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class teacherNotification extends Fragment {

    private List<notificationType> items;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container,false);
        items = new ArrayList<notificationType>();
        items.add(new teacherStudentJoined("Kristian Francisco", "Android programming"));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new teacherNotificationRecycler(((TeacherActivity)getActivity()).getApplicationContext(), items);
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}
