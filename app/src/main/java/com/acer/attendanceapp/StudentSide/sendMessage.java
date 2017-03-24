package com.acer.attendanceapp.StudentSide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acer.attendanceapp.Models.studentMessagesModel;
import com.acer.attendanceapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class sendMessage extends DialogFragment {

    private List<studentMessagesModel> studentMessagesModels;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);

        studentMessagesModels = new ArrayList<studentMessagesModel>();
        studentMessagesModels.add(new studentMessagesModel(R.drawable.professor_pic, "Kristian Francisco"));
        studentMessagesModels.add(new studentMessagesModel(R.drawable.professor_pic, "Kristian Francisco"));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new sendMessageRecycler(((StudentActivity)getActivity()).getApplicationContext(), studentMessagesModels, ((StudentActivity)getActivity()), this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }
}
