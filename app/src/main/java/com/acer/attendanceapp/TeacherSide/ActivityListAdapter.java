package com.acer.attendanceapp.TeacherSide;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acer.attendanceapp.Models.ClassModel;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Acer on 22/03/2017.
 */

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ViewHolder> {

    Context context;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private FirebaseStorage firebaseStorage;
    private StorageReference mStorageRef;
    private Query query;

    private ArrayList<ClassModel> classesList;

    public ActivityListAdapter(Context context) {
        this.context = context;

        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        mStorageRef = firebaseStorage.getReference();
        classesList = new ArrayList<>();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        Log.d("kobe","ni sud siya sa adapter");

        query = mRef.child("Classes")
                .child(user.getUid());

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                query = mRef.child("Classes")
                        .child(user.getUid())
                        .child(dataSnapshot.getKey());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ClassModel classModel = dataSnapshot.getValue(ClassModel.class);
                        classesList.add(classModel);
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
    public ActivityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_class_card_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActivityListAdapter.ViewHolder holder, int position) {
        holder.className.setText(classesList.get(position).getSubjectName());
        holder.schedule.setText(classesList.get(position).getDay() + " " + classesList.get(position).getTime());
        holder.schoolName.setText(classesList.get(position).getSchoolName());
        Glide.with(context).load(Uri.parse(classesList.get(position).getClassPic())).into(holder.classPic);gi
    }

    @Override
    public int getItemCount() {
        return classesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView className, schoolName, schedule;
        private ImageView classPic;
        private FrameLayout picColor;

        public ViewHolder(final View itemView) {
            super(itemView);
            className = (TextView) itemView.findViewById(R.id.subjectName);
            schoolName = (TextView) itemView.findViewById(R.id.schoolName);
            schedule = (TextView) itemView.findViewById(R.id.schedule);
            classPic = (ImageView) itemView.findViewById(R.id.classPic);
            picColor = (FrameLayout) itemView.findViewById(R.id.picColor);
        }
    }
}
