package com.acer.attendanceapp.StudentSide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acer.attendanceapp.Models.studentClassesModel;
import com.acer.attendanceapp.R;

import java.util.List;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentClassRecycler extends RecyclerView.Adapter<studentClassRecycler.ViewHolder> {

    private Context mContext;
    private List<studentClassesModel> studentClasses;

    public studentClassRecycler(Context mContext, List<studentClassesModel> studentClasses) {
        this.mContext = mContext;
        this.studentClasses = studentClasses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_classes, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mSubjectName.setText(studentClasses.get(position).getmSubject());
        holder.mSchedule.setText(studentClasses.get(position).getmSchedule());
        holder.mVenue.setText(studentClasses.get(position).getmVenue());
        holder.mProfName.setText(studentClasses.get(position).getmProfessor());
        holder.mProfPic.setImageResource(studentClasses.get(position).getmProfPic());
        holder.mNumOfAbsences.setText(studentClasses.get(position).getNumOfAbsences()+"");
    }

    @Override
    public int getItemCount() {
        return studentClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        
        private TextView mSubjectName;
        private TextView mSchedule;
        private TextView mVenue;
        private TextView mProfName;
        private ImageView mProfPic;
        private TextView mNumOfAbsences;
        private Button mDrop;
        
        public ViewHolder(View itemView) {
            super(itemView);
            
            mSubjectName = (TextView) itemView.findViewById(R.id.subName);
            mSchedule = (TextView) itemView.findViewById(R.id.subSched);
            mVenue = (TextView) itemView.findViewById(R.id.subVenue);
            mProfName = (TextView) itemView.findViewById(R.id.profName);
            mProfPic = (ImageView) itemView.findViewById(R.id.profPic);
            mNumOfAbsences = (TextView) itemView.findViewById(R.id.numOfAbsences);

            mDrop = (Button) itemView.findViewById(R.id.dropClass);
            
            mDrop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "DROPPED", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
