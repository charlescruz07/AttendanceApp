package com.acer.attendanceapp.StudentSide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.acer.attendanceapp.Models.studentMessagesModel;
import com.acer.attendanceapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class sendMessageRecycler extends RecyclerView.Adapter<sendMessageRecycler.ViewHolder>{

    private Context mContext;
    private List<studentMessagesModel> studentMessages;
    private StudentActivity studentActivity;
    private sendMessage sendMessage;

    public sendMessageRecycler(Context mContext, List<studentMessagesModel> studentMessages, StudentActivity studentActivity, sendMessage sendMessage) {
        this.mContext = mContext;
        this.studentMessages = studentMessages;
        this.studentActivity = studentActivity;
        this.sendMessage = sendMessage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_messages, parent, false);
        sendMessageRecycler.ViewHolder vh = new sendMessageRecycler.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mStudentName.setText(studentMessages.get(position).getmStudentName());
        holder.mStudentImage.setImageResource(studentMessages.get(position).getmStudentPic());
    }

    @Override
    public int getItemCount() {
        return studentMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mStudentName;
        private CircleImageView mStudentImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mStudentName = (TextView) itemView.findViewById(R.id.messageName);
            mStudentImage = (CircleImageView) itemView.findViewById(R.id.messagePic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //studentActivity.goReply(1);
                    Toast.makeText(mContext, "clicked at position " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    sendMessage.dismiss();
                }
            });
        }
    }
}
