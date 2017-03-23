package com.acer.attendanceapp.TeacherSide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acer.attendanceapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Acer on 23/03/2017.
 */

public class ActivityAttendeesAdapter extends RecyclerView.Adapter<ActivityAttendeesAdapter.ViewHolder> {

    private Context context;
    private String[] studentList = {
            "Charles Cruz attended on March 24, 2017",
            "Tyler Lim attended on March 24, 2017",
            "Kristian Francisco attended on March 24, 2017",
            "Deceree Quiamco attended on March 24, 2017",
            "Kobe Relativo attended on March 24, 2017",
            "Anton Ven Wycoco attended on March 24, 2017",
            "Kirster Kyle Quinio attended on March 24, 2017",
            "Ice Chavez on March 24, 2017",
            "Franz Joseph Paran attended on March 24, 2017",
            "Gil Canedo attended on March 24, 2017",
            "Elisha Anora attended on March 24, 2017",
    };

    public ActivityAttendeesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ActivityAttendeesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_details_teacher_attendees_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActivityAttendeesAdapter.ViewHolder holder, int position) {
        holder.student.setText(studentList[position]);
    }

    @Override
    public int getItemCount() {
        return studentList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView student;

        public ViewHolder(final View itemView){
            super(itemView);
            student = (TextView) itemView.findViewById(R.id.student);
        }
    }
}
