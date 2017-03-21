package com.acer.attendanceapp.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.acer.attendanceapp.R;
import com.acer.attendanceapp.StudentSide.StudentActivity;
import com.acer.attendanceapp.TeacherSide.TeacherActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Acer on 20/03/2017.
 */

public class RolePickActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private RelativeLayout teacherBtn,studentBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_pick_layout);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        teacherBtn = (RelativeLayout) findViewById(R.id.prof_btn);
        studentBtn = (RelativeLayout) findViewById(R.id.student_btn);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.prof_btn){
            mRef.child("Users").child(user.getUid()).child("Role").setValue("Teacher");
            finish();
            startActivity(new Intent(RolePickActivity.this, TeacherActivity.class));
        }else if(v.getId() == R.id.student_btn){
            mRef.child("Users").child(user.getUid()).child("Role").setValue("Student");
            finish();
            startActivity(new Intent(RolePickActivity.this, StudentActivity.class));
        }
    }
}
