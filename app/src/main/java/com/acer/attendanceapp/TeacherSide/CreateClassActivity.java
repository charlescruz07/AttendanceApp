package com.acer.attendanceapp.TeacherSide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.acer.attendanceapp.Models.ClassModel;
import com.acer.attendanceapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

/**
 * Created by Acer on 21/03/2017.
 */

public class CreateClassActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imagePicker,picHolder;
    private EditText schoolTxt, roomTxt, nameTxt,dayTxt,timeTxt,classKey;
    private ProgressBar progressBar;
    private Uri uri;
    private int PICK_IMAGE_REQUEST = 7;
    private FirebaseStorage firebaseStorage;
    private StorageReference mStorageRef;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef,mref2;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        imagePicker = (ImageView) findViewById(R.id.image_picker);
        picHolder = (ImageView) findViewById(R.id.picHolder);
        nameTxt = (EditText) findViewById(R.id.nameTxt);
        dayTxt = (EditText) findViewById(R.id.dayTxt);
        timeTxt = (EditText) findViewById(R.id.timeTxt);
        schoolTxt = (EditText) findViewById(R.id.schoolTxt);
        roomTxt = (EditText) findViewById(R.id.roomTxt);
        classKey = (EditText) findViewById(R.id.classKeyTxt);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference().child("Classes").child(mUser.getUid()).push();
        firebaseStorage = FirebaseStorage.getInstance();
        mStorageRef = firebaseStorage.getReference("ClassPictures");
        Log.d("charles",mStorageRef.toString());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.image_picker){Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE_REQUEST);

        }else if(v.getId() == R.id.submitBtn){
            try {
                progressBar.setVisibility(View.VISIBLE);
                mStorageRef.child(mUser.getUid()).child(uri.getLastPathSegment()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ClassModel classModel = new ClassModel();
                        classModel.setSchoolName(schoolTxt.getText().toString());
                        classModel.setSubjectName(nameTxt.getText().toString());
                        classModel.setDay(dayTxt.getText().toString());
                        classModel.setTime(timeTxt.getText().toString());
                        classModel.setRoom(roomTxt.getText().toString());
                        classModel.setClassKey(classKey.getText().toString());
                        classModel.setClassPic(taskSnapshot.getDownloadUrl().toString());
                        Log.d("charles",taskSnapshot.toString());
                        mRef.setValue(classModel);
//                        mref2.setValue("wasa");
                        progressBar.setVisibility(View.GONE);
                        finish();
                        startActivity(new Intent(CreateClassActivity.this, TeacherActivity.class));
                    }
                });
            }catch (Exception e){
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(this, "There seems to be a problem.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                picHolder.setVisibility(View.VISIBLE);
                picHolder.getLayoutParams().height = 700;
                picHolder.requestLayout();
                picHolder.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
