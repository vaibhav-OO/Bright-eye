package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class queryNfeedback extends AppCompatActivity {

    TextView query,feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_nfeedback);

        query=findViewById(R.id.userquery);
        feedback=findViewById(R.id.userfeedback);

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), userContactUs.class));

            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), userFeedback.class));

            }
        });
    }
}