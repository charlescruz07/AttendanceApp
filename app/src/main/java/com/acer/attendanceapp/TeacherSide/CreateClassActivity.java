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
import android.widget.EditText;
import android.widget.ImageView;

import com.acer.attendanceapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

/**
 * Created by Acer on 21/03/2017.
 */

public class CreateClassActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imagePicker,picHolder;
    EditText nameTxt,dayTxt,timeTxt;
    Uri uri;
    private int PICK_IMAGE_REQUEST = 7;
    FirebaseStorage firebaseStorage;
    StorageReference mStorageRef;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        imagePicker = (ImageView) findViewById(R.id.image_picker);
        picHolder = (ImageView) findViewById(R.id.picHolder);
        nameTxt = (EditText) findViewById(R.id.nameTxt);
        dayTxt = (EditText) findViewById(R.id.dayTxt);
        timeTxt = (EditText) findViewById(R.id.timeTxt);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        //mStorageRef.getRoot().child("Class Pictures");
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
            mStorageRef.child("wasa").putFile(uri);
//            Log.d("charles",mStorageRef.getRoot().toString());
            finish();
            startActivity(new Intent(CreateClassActivity.this,TeacherActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                picHolder.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
