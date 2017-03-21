package com.acer.attendanceapp.StudentSide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.acer.attendanceapp.LoginSignup.LoginActivity;
import com.acer.attendanceapp.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Acer on 20/03/2017.
 */

public class StudentActivity extends AppCompatActivity implements View.OnClickListener{

    private Button logoutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_layout);
        logoutBtn = (Button) findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.logout_btn){
            mAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(StudentActivity.this, LoginActivity.class));
        }
    }
}
