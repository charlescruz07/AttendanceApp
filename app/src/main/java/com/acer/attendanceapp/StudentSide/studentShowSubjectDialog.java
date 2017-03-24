package com.acer.attendanceapp.StudentSide;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.acer.attendanceapp.R;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentShowSubjectDialog extends DialogFragment{

    private Button mCancel, mOk;

    public studentShowSubjectDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_show_subject_dialog, container);

        mCancel = (Button) view.findViewById(R.id.btnCancel);
        mOk = (Button) view.findViewById(R.id.btnConfirm);

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(((StudentActivity) getActivity()), "Class Added", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }

}
