package com.example.bright_eyed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Feedback extends AppCompatActivity {

    Button saveFeedback;
    EditText uploadfeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        uploadfeedback=findViewById(R.id.feedback);;

        saveFeedback=findViewById(R.id.feedbackbtn);

        saveFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData1();
            }
        });
    }

    public void saveData1(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Feedback.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        uploadData1();

    }
    public void uploadData1(){
        String feedback = uploadfeedback.getText().toString();

        DataFeedback dataClass = new DataFeedback(feedback);
        //We are changing the child from title to currentDate,
        // because we will be updating title as well and it may affect child value.
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("UserFeedback").child(currentDate)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Feedback.this, "Your Feedback Is Save In System", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Feedback.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}