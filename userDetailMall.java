package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class userDetailMall extends AppCompatActivity {

    TextView detailName,detailAdd,detailContact;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_mall);

        detailImage=findViewById(R.id.detailImage);
        detailName=findViewById(R.id.detailName);
        detailAdd=findViewById(R.id.detailAdd);
        detailContact=findViewById(R.id.detailContact);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            detailAdd.setText(bundle.getString("Address"));
            detailName.setText(bundle.getString("Name"));
            detailContact.setText(bundle.getString("Contact"));

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }
}