package com.example.bright_eyed;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class uploadcoupencafe extends AppCompatActivity {

    Button saveButton;
    EditText uploadCoupencode,uploadDetail,uploadDiscount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadcoupencafe);

        uploadCoupencode=findViewById(R.id.uploadCoupenCode);
        uploadDetail=findViewById(R.id.uploadDetail);
        uploadDiscount=findViewById(R.id.uploadDiscount);

        saveButton=findViewById(R.id.saveButton);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }
    public void saveData(){

        AlertDialog.Builder builder = new AlertDialog.Builder(uploadcoupencafe.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        uploadData();

    }
    public void uploadData(){
        String code = uploadCoupencode.getText().toString();
        String detail = uploadDetail.getText().toString();
        String discount = uploadDiscount.getText().toString();


        DataCafeClass dataClass = new DataCafeClass(code, detail, discount);
        //We are changing the child from title to currentDate,
        // because we will be updating title as well and it may affect child value.
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("CoupenCafe").child(currentDate)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(uploadcoupencafe.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(uploadcoupencafe.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}