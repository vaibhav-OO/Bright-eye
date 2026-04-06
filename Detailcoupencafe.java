package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;

public class Detailcoupencafe extends AppCompatActivity {

    TextView detailcode,coupendetail,coupendiscount;

    FloatingActionButton deleteButton,editButton;

    String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailcoupencafe);

        detailcode=findViewById(R.id.dcoupencode);
        coupendetail=findViewById(R.id.dcoupendetail);
        coupendiscount=findViewById(R.id.dcoupendiscount);

        deleteButton=findViewById(R.id.deleteButton);
        editButton=findViewById(R.id.editButton);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            detailcode.setText(bundle.getString("code"));
            coupendetail.setText(bundle.getString("detail"));
            coupendiscount.setText(bundle.getString("discount"));
            key=bundle.getString("Key");
        }

    }
}