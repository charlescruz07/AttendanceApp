package com.acer.attendanceapp.TeacherSide;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acer.attendanceapp.LoginSignup.LoginActivity;
import com.acer.attendanceapp.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Acer on 24/03/2017.
 */

public class TeacherProfile extends Fragment {

    private CircleImageView teacherPic;
    private TextView classCount, teacherName;
    private ImageView logoutBtn;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private int count = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.teacher_profile_layout, container, false);

        classCount = (TextView) rootView.findViewById(R.id.countzz);
        logoutBtn = (ImageView) rootView.findViewById(R.id.logout_btn);
        teacherPic = (CircleImageView) rootView.findViewById(R.id.teacherPic);
        teacherName = (TextView) rootView.findViewById(R.id.teacherName);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Glide.with(getActivity()).load(mUser.getPhotoUrl()).into(teacherPic);
        teacherName.setText(mUser.getDisplayName());

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference().child("Classes").child(mUser.getUid());

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                count++;
                classCount.setText(count + "");
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

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        count = 0;
    }
}
