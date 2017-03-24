package com.acer.attendanceapp.StudentSide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acer.attendanceapp.Models.studentClassesModel;
import com.acer.attendanceapp.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentClassRecycler extends RecyclerView.Adapter<studentClassRecycler.ViewHolder> {

    private Context mContext;
    private List<studentClassesModel> studentClasses;
    private List<String> keys = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;

    public studentClassRecycler(Context mContext, List<studentClassesModel> studentClasses, String key) {
        this.mContext = mContext;
        this.studentClasses = studentClasses;
        this.keys.add(key);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_classes, parent, false);
        ViewHolder vh = new ViewHolder(view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mSubjectName.setText(studentClasses.get(position).getmSubject());
        holder.mSchedule.setText(studentClasses.get(position).getmSchedule());
        holder.mVenue.setText(studentClasses.get(position).getmVenue());
        holder.mProfName.setText(studentClasses.get(position).getmProfessor());
//        holder.mProfPic.setImageResource(studentClasses.get(position).getmProfPic());
        Glide.with(mContext).load(studentClasses.get(position).getmProfPic()).into(holder.mProfPic);
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
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    mRef = mRef.child("StudentSubs").child(user.getUid()).child(keys.get(getAdapterPosition()));
                    mRef.setValue(null);

//                    Log.d("charles",mRef.child("StudentSubs").child(keys.get(getAdapterPosition())).getRef().toString());
//                    mRef.child("ClassParticipants");
//                    mRef.addChildEventListener(new ChildEventListener() {
//                        @Override
//                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                            final String tID = dataSnapshot.getKey();
//                            mRef.child("ClassParticipants").child(tID);
//                            mRef.addChildEventListener(new ChildEventListener() {
//                                @Override
//                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                                    if(dataSnapshot.getKey().equals(user.getUid())){
//                                        mRef.child("ClassParticipants").child(tID).child(dataSnapshot.getKey());
//
//                                    }
//                                }
//
//                                @Override
//                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                                }
//
//                                @Override
//                                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                                }
//
//                                @Override
//                                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                        }
//
//                        @Override
//                        public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                        }
//
//                        @Override
//                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
                    Toast.makeText(mContext, "DROPPED", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
