package com.acer.attendanceapp.StudentSide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acer.attendanceapp.Models.ClassModel;
import com.acer.attendanceapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentAddClassDialog extends DialogFragment {

    private Button mOk, mCancel;
    private EditText editText;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private Query query;
    private ArrayList<String> keyList;

    public studentAddClassDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Enter the key of the class");
        View view = inflater.inflate(R.layout.student_add_class_dialog, container);

        mCancel = (Button) view.findViewById(R.id.studentAddClassCancel);
        mOk = (Button) view.findViewById(R.id.studentAddClassOK);
        editText = (EditText) view.findViewById(R.id.studentAddKey);
        keyList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference().child("Classes");

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final String teacherID = dataSnapshot.getKey();
                mRef = firebaseDatabase.getReference().child("Classes").child(teacherID);
                mRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        final String classID = dataSnapshot.getKey();
                        mRef = firebaseDatabase.getReference().child("Classes").child(teacherID).child(classID);
                        Log.d("charles",mRef.getRef().toString());
                        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                ClassModel classModel = dataSnapshot.getValue(ClassModel.class);
                                Log.d("charles",classModel.getClassKey());
                                keyList.add(classModel.getClassKey());
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
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

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        mOk.setOnClickListener(new View.OnClickListener() {
            boolean found = false;
            @Override
            public void onClick(View v) {
                for (String str : keyList) {
                    if (editText.getText().toString().equals(str)) {
                        ((StudentActivity) getActivity()).showDialogs(2,str);
                        getDialog().dismiss();
                        found = true;
                    }
                }
                if(!found)
                    Toast.makeText(getActivity(), "Subject not found!", Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }


}
