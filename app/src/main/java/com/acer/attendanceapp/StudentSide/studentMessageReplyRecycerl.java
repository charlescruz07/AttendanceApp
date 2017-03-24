package com.acer.attendanceapp.StudentSide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.acer.attendanceapp.Models.basicNotificationModel;
import com.acer.attendanceapp.Models.mudawatModel;
import com.acer.attendanceapp.Models.musendModel;
import com.acer.attendanceapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Kristian Francisco on 3/25/2017.
 */

public class studentMessageReplyRecycerl extends RecyclerView.Adapter<studentMessageReplyRecycerl.ViewHolder>{

    private final Context mContext;
    private final List<studentReplies> mItems;

    public studentMessageReplyRecycerl(Context mContext, List<studentReplies> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getStudentReplyType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType){

            case studentReplies.MUDAWAT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_mudawat, parent, false);
                return new studentMessageReplyRecycerl.ViewHolderMudawat(view);

            case studentReplies.MUSEND:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_musend, parent, false);
                return new studentMessageReplyRecycerl.ViewHolderMusend(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        studentReplies item = mItems.get(position);
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

        public void bindType(studentReplies item) {

        }
    }

    private class ViewHolderMudawat extends ViewHolder {

        private TextView mMessage;
        private CircleImageView mPic;

        public ViewHolderMudawat(View view) {
            super(view);

            mMessage = (TextView) view.findViewById(R.id.mudawatMessage);
            mPic = (CircleImageView) view.findViewById(R.id.mudawatPic);

        }

        public void bindType(ListItem item) {
           mMessage.setText(((mudawatModel)item).getMessage());
            mPic.setImageResource(((mudawatModel)item).getPicture());
        }
    }

    private class ViewHolderMusend extends ViewHolder {

        private TextView mMessage;

        public ViewHolderMusend(View view) {
            super(view);
            mMessage = (TextView) view.findViewById(R.id.musend);
        }

        public void bindType(ListItem item) {
            mMessage.setText(((musendModel) item).getMessage());
        }

    }
}
