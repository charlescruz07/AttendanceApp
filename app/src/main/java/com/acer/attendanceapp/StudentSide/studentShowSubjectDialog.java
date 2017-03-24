package com.acer.attendanceapp.StudentSide;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acer.attendanceapp.Models.ClassModel;
import com.acer.attendanceapp.Models.ClassParticipant;
import com.acer.attendanceapp.Models.Users;
import com.acer.attendanceapp.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentShowSubjectDialog extends DialogFragment{

    private Button mCancel, mOk;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private Query query;
    private ImageView profPic;
    private TextView profName,subName,subSched,subRoom;

    private String tID,subID;
    public studentShowSubjectDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_show_subject_dialog, container);

        mCancel = (Button) view.findViewById(R.id.btnCancel);
        mOk = (Button) view.findViewById(R.id.btnConfirm);
        profPic = (ImageView) view.findViewById(R.id.profPic);
        profName = (TextView) view.findViewById(R.id.profName);
        subName = (TextView) view.findViewById(R.id.subName);
        subSched = (TextView) view.findViewById(R.id.subSched);
        subRoom = (TextView) view.findViewById(R.id.subRoom);

        final String key = getArguments().getString("class key");

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
                                if(classModel.getClassKey().equals(key)){
                                    Log.d("charles",classModel.getSubjectName());
                                    subName.setText(classModel.getSubjectName());
                                    subSched.setText(classModel.getDay() + " " + classModel.getTime() );
                                    subRoom.setText(classModel.getRoom());
                                    mRef = firebaseDatabase.getReference().child("Users").child(teacherID).child("Name");
                                    Log.d("charles",mRef.getRef().toString());
                                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            profName.setText(dataSnapshot.getValue().toString());
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                    mRef = firebaseDatabase.getReference().child("Users").child(teacherID).child("Picture");
                                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            Glide.with(getActivity()).load(Uri.parse(dataSnapshot.getValue().toString())).into(profPic);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                    tID = teacherID;
                                    subID = classID;
                                }
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

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(((StudentActivity) getActivity()), "Class Added", Toast.LENGTH_SHORT).show();
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                mRef = firebaseDatabase.getReference().child("ClassParticipants").child(tID).child(subID).child(user.getUid());
                ClassParticipant classParticipant = new ClassParticipant();
                classParticipant.setStudentName(user.getDisplayName());
                classParticipant.setStudentImg(user.getPhotoUrl().toString());
                mRef.setValue(classParticipant);
                getDialog().dismiss();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }

}
