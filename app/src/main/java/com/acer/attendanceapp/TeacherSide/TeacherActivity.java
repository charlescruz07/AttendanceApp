package com.acer.attendanceapp.TeacherSide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.acer.attendanceapp.LoginSignup.LoginActivity;
import com.acer.attendanceapp.R;
import com.acer.attendanceapp.StudentSide.StudentActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Acer on 20/03/2017.
 */

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener{

    private Button logoutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_layout);
        logoutBtn = (Button) findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.logout_btn){
            mAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(TeacherActivity.this, LoginActivity.class));
        }else if(v.getId() == R.id.create_act){
            startActivity(new Intent(TeacherActivity.this,CreateClassActivity.class));
        }
    }
}
