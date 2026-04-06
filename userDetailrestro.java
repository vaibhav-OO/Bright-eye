package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class userDetailrestro extends AppCompatActivity {

    TextView detailName,detailAdd,detailContact,detailTiming;
    ImageView detailImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailrestro);

        detailImage=findViewById(R.id.detailImage);
        detailName=findViewById(R.id.detailName);
        detailAdd=findViewById(R.id.detailAdd);
        detailContact=findViewById(R.id.detailContact);
        detailTiming=findViewById(R.id.detailTime);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            detailAdd.setText(bundle.getString("Address"));
            detailName.setText(bundle.getString("Name"));
            detailContact.setText(bundle.getString("Contact"));
            detailTiming.setText(bundle.getString("Timing"));

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }
}