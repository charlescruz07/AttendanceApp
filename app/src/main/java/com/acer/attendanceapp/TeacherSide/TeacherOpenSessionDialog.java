package com.acer.attendanceapp.TeacherSide;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.acer.attendanceapp.Models.ClassModel;
import com.acer.attendanceapp.Models.SessionModel;
import com.acer.attendanceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Acer on 25/03/2017.
 */

public class TeacherOpenSessionDialog extends DialogFragment {

    private Button mOk, mCancel;
    private EditText editText;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private Query query;
    private ArrayList<String> keyList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Set secret code for this session: ");
        View rootView = inflater.inflate(R.layout.teacher_open_session_dialog,container,false);
        mCancel = (Button) rootView.findViewById(R.id.cancelBtn);
        mOk = (Button) rootView.findViewById(R.id.okBtn);
        editText = (EditText) rootView.findViewById(R.id.teacherAddKey);

        final String key = getArguments().getString("key");

        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference().child("ClassSessions").child(key).push();

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                mRef = firebaseDatabase.getReference()
                        .child("Classes")
                        .child(user.getUid()).child(key);
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ClassModel classModel = dataSnapshot.getValue(ClassModel.class);
                        SessionModel sessionModel = new SessionModel();
                        sessionModel.setSessionKey(editText.getText().toString());
                        sessionModel.setClassName(classModel.getSubjectName());
                        sessionModel.setSched(classModel.getDay() + " " + classModel.getTime());
                        sessionModel.setVenue(classModel.getRoom());
                        sessionModel.setKey(key);
                        mRef = firebaseDatabase.getReference().child("ClassSessions").child(key);
                        mRef.setValue(sessionModel);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return rootView;
    }
}
