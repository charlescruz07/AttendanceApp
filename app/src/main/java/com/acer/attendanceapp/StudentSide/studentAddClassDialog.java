package com.acer.attendanceapp.StudentSide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.acer.attendanceapp.R;

/**
 * Created by Kristian Francisco on 3/24/2017.
 */

public class studentAddClassDialog extends DialogFragment {

    private Button mOk, mCancel;


    public studentAddClassDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Enter the key of the class");
        View view = inflater.inflate(R.layout.student_add_class_dialog, container);

        mCancel = (Button) view.findViewById(R.id.studentAddClassCancel);
        mOk = (Button) view.findViewById(R.id.studentAddClassOK);

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudentActivity)getActivity()).showDialogs(2);
                getDialog().dismiss();
            }
        });

        return  view;
    }


}
