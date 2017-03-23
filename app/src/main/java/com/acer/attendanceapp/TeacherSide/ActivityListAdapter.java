package com.acer.attendanceapp.TeacherSide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import java.util.Random;

/**
 * Created by Acer on 22/03/2017.
 */

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ViewHolder> {

    Context context;
    private ArrayList<ClassModel> classesList;
    private ArrayList<String> subjectPrimaryKeys;
    private String[] hexCodes = {
            "#B3B6B7",
            "#FFC300",
            "#C0392B",
            "#145A32",
            "#154360"
    };

    public ActivityListAdapter(Context context, ArrayList<ClassModel> classesList, ArrayList<String> subjectPrimaryKeys) {
        this.context = context;
        this.classesList = classesList;
        this.subjectPrimaryKeys = subjectPrimaryKeys;
        Log.d("charles",classesList.size() + "mao ni ang size");
    }

    @Override
    public ActivityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_class_card_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActivityListAdapter.ViewHolder holder, int position) {

        int random = new Random().nextInt(hexCodes.length-1);
        holder.className.setText(classesList.get(position).getSubjectName());
        holder.schedule.setText(classesList.get(position).getDay() + " " + classesList.get(position).getTime());
        holder.schoolName.setText(classesList.get(position).getSchoolName());
        Glide.with(context).load(Uri.parse(classesList.get(position).getClassPic())).into(holder.classPic);
        holder.picColor.setBackgroundColor(Color.parseColor(hexCodes[random]));

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,ClassDetailsActivity.class).putExtra("key",subjectPrimaryKeys.get(getAdapterPosition())));
                }
            });
        }
    }
}
