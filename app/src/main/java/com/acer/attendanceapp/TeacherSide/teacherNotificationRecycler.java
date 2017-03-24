package com.acer.attendanceapp.TeacherSide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.acer.attendanceapp.Models.basicNotificationModel;
import com.acer.attendanceapp.Models.teacherStudentJoined;
import com.acer.attendanceapp.R;
import com.acer.attendanceapp.StudentSide.ListItem;
import com.acer.attendanceapp.StudentSide.studentNotificationRecycler;

import java.util.List;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class teacherNotificationRecycler extends RecyclerView.Adapter<teacherNotificationRecycler.ViewHolder> {

    private Context mContext;
    private List<notificationType> mItems;

    public teacherNotificationRecycler(Context mContext, List<notificationType> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getNotificationType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){

            case notificationType.JOINED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_student_joined, parent, false);
                return new teacherNotificationRecycler.ViewHolderJoined(view);

            case notificationType.DROP:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_student_dropped, parent, false);
                return new teacherNotificationRecycler.ViewHolderDrop(view);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        notificationType item = mItems.get(position);
        holder.bindType(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bindType(notificationType item) {

        }
    }

    private class ViewHolderJoined extends ViewHolder {

        private TextView mStudentName;
        private TextView mSubjectName;

        public ViewHolderJoined(View view) {
            super(view);

            mStudentName = (TextView) view.findViewById(R.id.teacherJoinedStudentName);
            mSubjectName = (TextView) view.findViewById(R.id.teacherJoinedSubjectName);

        }

        public void bindType(ListItem item) {
            mSubjectName.setText(((teacherStudentJoined)item).getmSubjectName());
            mStudentName.setText(((teacherStudentJoined)item).getmStudentName());
        }
    }

    private class ViewHolderDrop extends ViewHolder {
        public ViewHolderDrop(View view) {
            super(view);
        }
    }
}
