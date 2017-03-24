package com.acer.attendanceapp.StudentSide;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.acer.attendanceapp.LoginSignup.LoginActivity;
import com.acer.attendanceapp.R;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.DrawerView;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerHeaderItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;

/**
 * Created by Kristian Francisco on 20/03/2017.
 */

public class StudentActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private AHBottomNavigation mBottomNav;
    private Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    private DrawerView mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private FloatingActionButton mFab;

    private static studentNotification studentNotification = new studentNotification();
    private static studentClasses studentClasses = new studentClasses();
    private static studentMessages studentMessages = new studentMessages();
    private static studentMessageReply studentMessageReply = new studentMessageReply();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_layout);


        init();
        initBottomBar();
        initDrawer();

    }



    //initialization of components
    private void init() {

        mAuth = FirebaseAuth.getInstance();
        mBottomNav = (AHBottomNavigation) findViewById(R.id.student_bottom_navigation);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Home");
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawer = (DrawerView) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ){

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        mFab = (FloatingActionButton) findViewById(R.id.studentFab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDialogs(1);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.student_fragment_container, studentClasses)
                .commit();

    }

    private void initDrawer() {

        mDrawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawer.selectItem(0);
        mDrawerLayout.closeDrawer(mDrawer);
        mDrawer.setOnItemClickListener(new DrawerItem.OnItemClickListener() {
            @Override
            public void onClick(DrawerItem drawerItem, long id, int position) {

                switch(position){
                    case 0:
                        mToolbar.setTitle("Home");
                        break;
                    case 1:
                        mToolbar.setTitle("Profile");
                        break;
                    case 2:
                        mToolbar.setTitle("Statistics");
                        break;
                    case 3:
                        mToolbar.setTitle("Classmates");
                        break;
                    case 4:
                        mToolbar.setTitle("Settings");
                        break;
                    case 5:
                        mAuth.signOut();
                        Intent intent = new Intent(StudentActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                mDrawer.selectItem(position);
                mDrawerLayout.closeDrawer(mDrawer);
            }
        });

        //adding profiles to the drawer
        mDrawer.addProfile(new DrawerProfile()
                .setId(1)
                .setRoundedAvatar((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.professor_pic))
                .setBackground(ContextCompat.getDrawable(this, R.drawable.cat_wide_1))
                .setName(getString(R.string.lorem_ipsum_short))
                .setDescription(getString(R.string.lorem_ipsum_medium))
        );



        //adding items to the drawer
        mDrawer.addItem(new DrawerItem()
                .setImage(ContextCompat.getDrawable(this, R.drawable.zzz_email))
                .setTextPrimary("Home")
                .setTextSecondary("Your Dashboard")
        );

        mDrawer.addItem(new DrawerItem()
                .setImage(ContextCompat.getDrawable(this, R.drawable.zzz_face_profile))
                .setTextPrimary("Profile")
                .setTextSecondary("View and Edit your profile")
        );

        mDrawer.addItem(new DrawerItem()
                .setImage(ContextCompat.getDrawable(this, R.drawable.zzz_table_large))
                .setTextPrimary("Statistics")
                .setTextSecondary("View your performance")
        );

        mDrawer.addItem(new DrawerItem()
                .setImage(ContextCompat.getDrawable(this, R.drawable.zzz_people))
                .setTextPrimary("Classmates")
                .setTextSecondary("View your classmates")
        );

        mDrawer.addItem(new DrawerItem()
                .setImage(ContextCompat.getDrawable(this, R.drawable.zzz_settings))
                .setTextPrimary("Settings")
                .setTextSecondary("Set your preference")
        );

        mDrawer.addItem(new DrawerItem()
                .setImage(ContextCompat.getDrawable(this, R.drawable.zzz_logout))
                .setTextPrimary("Log out")
                .setTextSecondary("See you later")
        );



    }

    private void initBottomBar() {

        mBottomNav.addItem(
                new AHBottomNavigationItem(
                        "My classes", R.drawable.zzz_bulletin_board, R.color.colorPrimary
                )
        );

        mBottomNav.addItem(
                new AHBottomNavigationItem(
                        "Notifications", R.drawable.zzz_earth, R.color.colorPrimaryDark
                )
        );

        mBottomNav.addItem(
                new AHBottomNavigationItem(
                        "Messages", R.drawable.zzz_message_processing, R.color.colorPrimaryDark
                )
        );

        mBottomNav.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position){

                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.student_fragment_container, studentClasses)
                                .commit();
                        mFab.setVisibility(View.VISIBLE);
                        mBottomNav.setNotification("", 0);
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.student_fragment_container, studentNotification)
                                .commit();
                        mBottomNav.setNotification("", 1);
                        mFab.setVisibility(View.GONE);
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.student_fragment_container, studentMessages)
                                .commit();
                        mBottomNav.setNotification("", 2);
                        mFab.setVisibility(View.GONE);
                        break;

                }

                return true;
            }
        });

//        Adding notifications
        mBottomNav.setNotification("1", 1);
//        OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_back))
//                .setTextColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_text))
//                .build();
//        bottomNavigation.setNotification(notification, 1);

        mBottomNav.setDefaultBackgroundColor(getResources().getColor(R.color.color_bottombar));
        mBottomNav.setAccentColor(getResources().getColor(R.color.color_bottombar_active));
        mBottomNav.setInactiveColor(getResources().getColor(R.color.color_bottombar_inactive));
        mBottomNav.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mBottomNav.setCurrentItem(0);
        mBottomNav.setForceTint(true);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    public void showDialogs(int identifier){

        switch (identifier){
            case 1:
                studentAddClassDialog studentAddClassDialog = new studentAddClassDialog();
                studentAddClassDialog.setRetainInstance(true);
                studentAddClassDialog.show(getSupportFragmentManager(), "add class");
                break;
            case 2:
                studentShowSubjectDialog studentShowSubjectDialog = new studentShowSubjectDialog();
                studentShowSubjectDialog.setRetainInstance(true);
                studentShowSubjectDialog.show(getSupportFragmentManager(), "Confirm subject");
                break;
        }

    }
    public void showDialogs(int identifier,String classKey){

        switch (identifier){
            case 1:
                studentAddClassDialog studentAddClassDialog = new studentAddClassDialog();
                studentAddClassDialog.setRetainInstance(true);
                studentAddClassDialog.show(getSupportFragmentManager(), "add class");
                break;
            case 2:
                Bundle bundle = new Bundle();
                bundle.putString("class key", classKey);
                studentShowSubjectDialog studentShowSubjectDialog = new studentShowSubjectDialog();
                studentShowSubjectDialog.setArguments(bundle);
                studentShowSubjectDialog.setRetainInstance(true);
                studentShowSubjectDialog.show(getSupportFragmentManager(), "Confirm subject");
                break;
        }

    }

    public void goReply(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.student_fragment_container, studentMessageReply)
                .commit();
    }

}
