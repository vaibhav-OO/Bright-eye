package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class userDetailHospital extends AppCompatActivity {
    TextView detailName,detailAdd,detailContact,detailTime,detailRating,detailSector;
    ImageView detailImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_hospital);

        detailImage=findViewById(R.id.detailImage);
        detailName=findViewById(R.id.detailName);
        detailAdd=findViewById(R.id.detailAdd);
        detailContact=findViewById(R.id.detailContact);
        detailTime=findViewById(R.id.detailTime);
        detailRating=findViewById(R.id.detailRating);
        detailSector=findViewById(R.id.detailSector);



        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            detailAdd.setText(bundle.getString("Address"));
            detailName.setText(bundle.getString("Name"));
            detailContact.setText(bundle.getString("Contact"));
            detailTime.setText(bundle.getString("Timing"));
            detailRating.setText(bundle.getString("Rating"));
            detailSector.setText(bundle.getString("Sector"));

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }
}