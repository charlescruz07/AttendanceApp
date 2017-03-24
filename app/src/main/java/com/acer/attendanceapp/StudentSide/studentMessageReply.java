package com.acer.attendanceapp.StudentSide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acer.attendanceapp.Models.mudawatModel;
import com.acer.attendanceapp.Models.musendModel;
import com.acer.attendanceapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class studentMessageReply extends Fragment {

    private List<studentReplies> items;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        items = new ArrayList<studentReplies>();

        items.add(new musendModel("ayooo"));
        items.add(new mudawatModel(R.drawable.professor_pic, "hello"));
        items.add(new musendModel("ayooo"));
        items.add(new mudawatModel(R.drawable.professor_pic, "hello"));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new studentMessageReplyRecycerl(((StudentActivity)getActivity()).getApplicationContext(), items);
        mRecyclerView.setAdapter(adapter);

        return view;
    }
}
