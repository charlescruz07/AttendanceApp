package com.acer.attendanceapp.StudentSide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acer.attendanceapp.Models.studentClassesModel;
import com.acer.attendanceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentClasses extends Fragment {

    private List<studentClassesModel> studentClassesModelList;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private Query query;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        studentClassesModelList = new ArrayList<studentClassesModel>();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        query = mRef.child("StudentSubs")
                .child(user.getUid());

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("charles","key: " + dataSnapshot.getKey());
                studentClassesModel studModel = dataSnapshot.getValue(studentClassesModel.class);
                studentClassesModelList.add(studModel);
                mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                layoutManager = new LinearLayoutManager(view.getContext());
                mRecyclerView.setLayoutManager(layoutManager);
                adapter = new studentClassRecycler(((StudentActivity)getActivity()).getApplicationContext(), studentClassesModelList, dataSnapshot.getKey());
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }
}
