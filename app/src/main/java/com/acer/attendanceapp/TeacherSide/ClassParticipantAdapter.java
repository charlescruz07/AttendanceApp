package com.acer.attendanceapp.TeacherSide;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acer.attendanceapp.Models.ClassParticipant;
import com.acer.attendanceapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Acer on 24/03/2017.
 */

public class ClassParticipantAdapter extends RecyclerView.Adapter<ClassParticipantAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ClassParticipant> participants;

    public ClassParticipantAdapter(Context context, ArrayList<ClassParticipant> participants){
        this.context = context;
        this.participants = participants;
    }

    @Override
    public ClassParticipantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_participant_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClassParticipantAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(Uri.parse(participants.get(position).getStudentImg())).into(holder.civ);
        String arr[] = participants.get(position).getStudentName().split(" ", 2);
        holder.studentName.setText(arr[0]);
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView civ;
        TextView studentName;

        public ViewHolder(View itemView) {
            super(itemView);
            civ = (CircleImageView)itemView.findViewById(R.id.faceImg);
            studentName = (TextView) itemView.findViewById(R.id.studentName);

        }
    }
}
