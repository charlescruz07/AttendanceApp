package com.acer.attendanceapp.StudentSide;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acer.attendanceapp.Models.SessionModel;
import com.acer.attendanceapp.Models.announcementNotificationModel;
import com.acer.attendanceapp.Models.basicNotificationModel;
import com.acer.attendanceapp.Models.decisionNotificationModel;
import com.acer.attendanceapp.Models.dropNotificationModel;
import com.acer.attendanceapp.Models.joinedNotificationModel;
import com.acer.attendanceapp.Models.tobeDroppedNotificationModel;
import com.acer.attendanceapp.R;
import com.acer.attendanceapp.TeacherSide.ActivityAttendeesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentNotification extends Fragment{

    private List<ListItem> items;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        SessionModel sessionModel = getArguments().getParcelable("notif");
        items = new ArrayList<ListItem>();
        items.add(new basicNotificationModel(sessionModel.getClassName(),sessionModel.getSched(),sessionModel.getVenue(), sessionModel.getKey()));
//        items.add(new basicNotificationModel("math","mwf 10:30-2:30","room bcl 5"));
//        items.add(new announcementNotificationModel("english"));
//        items.add(new decisionNotificationModel("Kristian Francisco", true, "Physics"));
//        items.add(new dropNotificationModel("Chemistry"));
//        items.add(new tobeDroppedNotificationModel("Math"));
//        items.add(new joinedNotificationModel("Free ELEC"));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new studentNotificationRecycler(((StudentActivity)getActivity()).getApplicationContext(), items, this);
        mRecyclerView.setAdapter(adapter);
        return view;
    }


    public void addNotif(List<ListItem> items, Context mContext){
        if(items!=null){
//            items.add(new basicNotificationModel(sessionModel.getClassName(),sessionModel.getSched(),sessionModel.getVenue(), sessionModel.getKey()));
            //adapter.notifyDataSetChanged();
            adapter = null;
            adapter = new studentNotificationRecycler(mContext, items, this);
        }
        else
            Log.d("kf", "null");

    }
}
