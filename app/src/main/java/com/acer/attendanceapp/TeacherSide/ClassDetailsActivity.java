package com.acer.attendanceapp.TeacherSide;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acer.attendanceapp.Models.ClassModel;
import com.acer.attendanceapp.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Acer on 23/03/2017.
 */

public class ClassDetailsActivity extends AppCompatActivity {

    private TextView subName,subSched,subSchool,subKey;
    private ImageView subClassPic,openSession;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private FirebaseStorage firebaseStorage;
    private StorageReference mStorageRef;
    private Query query;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_details_teacher_layout);

        subName = (TextView) findViewById(R.id.subName);
        subSched = (TextView) findViewById(R.id.subDateTimeRoom);
        subSchool = (TextView) findViewById(R.id.subSchool);
        subKey = (TextView) findViewById(R.id.subKey);
        subClassPic = (ImageView) findViewById(R.id.subClassPic);
        frameLayout = (FrameLayout) findViewById(R.id.classParticipantsHolder);
        openSession = (ImageView) findViewById(R.id.openSession);
        mContext = this;
        final String key = getIntent().getStringExtra("key");

        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        mStorageRef = firebaseStorage.getReference();

        openSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key",key);
                TeacherOpenSessionDialog teacherOpenSessionDialog = new TeacherOpenSessionDialog();
                teacherOpenSessionDialog.setArguments(bundle);
                teacherOpenSessionDialog.show(getFragmentManager(),"charles");
            }
        });

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        query = mRef.child("Classes")
                .child(user.getUid())
                .child(key);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ClassModel classModel = dataSnapshot.getValue(ClassModel.class);
                subName.setText(classModel.getSubjectName());
                subSched.setText(
                        classModel.getDay() + " " +
                        classModel.getTime() + " | " +
                        classModel.getRoom());
                subSchool.setText(classModel.getSchoolName());
                subKey.setText(classModel.getClassKey());
                Glide.with(mContext).load(Uri.parse(classModel.getClassPic())).into(subClassPic);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.classParticipantsHolder,new ActivityAtendeesFragment()).commit();
        fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("key",key);
        ClassParticipantFragment classParticipantFragment = new ClassParticipantFragment();
        classParticipantFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.studentListHolder,classParticipantFragment).commit();

    }
}
