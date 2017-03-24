package com.acer.attendanceapp.TeacherSide;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.acer.attendanceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * Created by Acer on 20/03/2017.
 */

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigation.OnMenuItemSelectionListener{

    private FirebaseAuth mAuth;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int fragmentIdentifier = 0;
    private BottomNavigation bottomNavigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_layout);
        mAuth = FirebaseAuth.getInstance();

        bottomNavigation = (BottomNavigation) findViewById(R.id.BottomNavigation);
        bottomNavigation.setOnMenuItemClickListener(this);

        frameLayout = (FrameLayout) findViewById(R.id.fragmentHolder);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder, new ActivityListFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.create_act){
            startActivity(new Intent(TeacherActivity.this,CreateClassActivity.class));
        }
    }

    @Override
    public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {
        Log.d("charles","ni select siya");
        switch (i1){
            case 0:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder, new ActivityListFragment()).addToBackStack(null).commit();
                fragmentIdentifier = 1;
                break;
            case 1:
                break;
            case 2:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder, new TeacherProfile()).addToBackStack(null).commit();
                fragmentIdentifier = 3;
        }
    }

    @Override
    public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {

    }
}
