package com.acer.attendanceapp.StudentSide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.acer.attendanceapp.Models.announcementNotificationModel;
import com.acer.attendanceapp.Models.basicNotificationModel;
import com.acer.attendanceapp.Models.decisionNotificationModel;
import com.acer.attendanceapp.Models.dropNotificationModel;
import com.acer.attendanceapp.Models.joinedNotificationModel;
import com.acer.attendanceapp.Models.tobeDroppedNotificationModel;
import com.acer.attendanceapp.R;

import java.util.List;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentNotificationRecycler extends RecyclerView.Adapter<studentNotificationRecycler.ViewHolder>{

    private final Context mContext;
//    private final List<ListItem> mItems = new List<ListItem>(
//            new basicNotificationModel("Math","MWF 10:30-11:30","ROOM BCL 5"){},
//    );
    private final List<ListItem> mItems;


    public studentNotificationRecycler(Context ctx, List<ListItem> items){
        mContext = ctx;
        mItems = items;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getListItemType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){

            case ListItem.BASIC:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basic_notification, parent, false);
                return new ViewHolderBasic(view);

            case ListItem.ANNOUNCEMENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_notification, parent, false);
                return new ViewHolderAnnouncement(view);

            case ListItem.DECISION:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.decision_notification, parent, false);
                return new ViewHolderDecision(view);

            case ListItem.DROPPED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.real_drop_notification, parent, false);
                return new ViewHolderDropped(view);

            case ListItem.JOINED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joined_notification, parent, false);
                return new ViewHolderJoinedt(view);

            case ListItem.TOBE_DROPPED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_notification, parent, false);
                return new ViewHolderToBeDropped(view);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListItem item = mItems.get(position);
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

        public void bindType(ListItem item) {
        }
    }

    private class ViewHolderBasic extends ViewHolder {

        private TextView mSubjectName;
        private TextView mSchedule;
        private TextView mVenue;
        private Button mAttend, mAbsent;

        public ViewHolderBasic(View view) {
            super(view);

            mSubjectName = (TextView) view.findViewById(R.id.basicSubjectName);
            mSchedule = (TextView) view.findViewById(R.id.basicSchedule);
            mVenue = (TextView) view.findViewById(R.id.basicVenue);
            mAttend = (Button) view.findViewById(R.id.basicAttend);
            mAbsent = (Button) view.findViewById(R.id.basicAbsent);
        }

        public void bindType(ListItem item) {
            mSubjectName.setText(((basicNotificationModel)item).getmSubjectName());
            mSchedule.setText(((basicNotificationModel)item).getmSchedule());
            mVenue.setText(((basicNotificationModel)item).getmVenue());
            mAbsent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "ABSENT NAKA", Toast.LENGTH_SHORT).show();
                }
            });
            mAttend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "ATTENDED NAKA", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class ViewHolderAnnouncement extends ViewHolder {

        private TextView mSubjectName;

        public ViewHolderAnnouncement(View view) {
            super(view);
            mSubjectName = (TextView) view.findViewById(R.id.announcementSubjectName);
        }

        public void bindType(ListItem item) {
            mSubjectName.setText(((announcementNotificationModel) item).getmSubjectName());
        }
    }

    private class ViewHolderDecision extends ViewHolder {

        private TextView mClassmateName;
        private TextView mWillAbsent;
        private TextView mSubjectName;
        private Button mAccept, mReject;

        public ViewHolderDecision(View view) {
            super(view);

            mClassmateName = (TextView) view.findViewById(R.id.decisionClassmateName);
            mWillAbsent = (TextView) view.findViewById(R.id.decisionWillAbsent);
            mSubjectName = (TextView) view.findViewById(R.id.decisionSubjectName);
            mAccept = (Button) view.findViewById(R.id.decisionAccept);
            mReject = (Button) view.findViewById(R.id.decisionReject);
        }

        public void bindType(ListItem item) {
            mSubjectName.setText(((decisionNotificationModel) item).getSubjectName());
            mClassmateName.setText(((decisionNotificationModel) item).getClassmateName());

            if(((decisionNotificationModel) item).isWillAbsent()){
                mWillAbsent.setText("ABSENT");
            }
            else
                mWillAbsent.setText("PRESENT");
            
            mAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "ACCEPTED", Toast.LENGTH_SHORT).show();
                }
            });
            
            mReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "REJECTED", Toast.LENGTH_SHORT).show();
                }
            });
            
            
        }

    }

    private class ViewHolderDropped extends ViewHolder {
        private TextView mSubjectName;

        public ViewHolderDropped(View view) {
            super(view);
            mSubjectName = (TextView) view.findViewById(R.id.realDropSubjectName);
        }

        public void bindType(ListItem item) {
            mSubjectName.setText(((dropNotificationModel) item).getSubjectName());
        }
    }

    private class ViewHolderJoinedt extends ViewHolder {
        private TextView mSubjectName;

        public ViewHolderJoinedt(View view) {
            super(view);
            mSubjectName = (TextView) view.findViewById(R.id.joinedSubjectName);
        }

        public void bindType(ListItem item) {
            mSubjectName.setText(((joinedNotificationModel) item).getSubjectName());
        }
    }

    private class ViewHolderToBeDropped extends ViewHolder {
        private TextView mSubjectName;

        public ViewHolderToBeDropped(View view) {
            super(view);
            mSubjectName = (TextView) view.findViewById(R.id.dropSubjectName);
        }

        public void bindType(ListItem item) {
            mSubjectName.setText(((tobeDroppedNotificationModel) item).getSubjectName());
        }
    }
}
