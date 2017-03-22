package com.acer.attendanceapp.TeacherSide;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.acer.attendanceapp.LoginSignup.LoginActivity;
import com.acer.attendanceapp.R;
import com.acer.attendanceapp.StudentSide.StudentActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Acer on 20/03/2017.
 */

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_layout);
        mAuth = FirebaseAuth.getInstance();

        frameLayout = (FrameLayout) findViewById(R.id.fragmentHolder);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder, new ActivityListFragment()).commit();
    }

    @Override
    public void onClick(View v) {
//        if(v.getId() == R.id.logout_btn){
//            mAuth.getInstance().signOut();
//            finish();
//            startActivity(new Intent(TeacherActivity.this, LoginActivity.class));
//        }else
            if(v.getId() == R.id.create_act){
            startActivity(new Intent(TeacherActivity.this,CreateClassActivity.class));
        }
    }
}
