package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.firebase.auth.FirebaseAuth;

public class admin_home extends AppCompatActivity {

    CardView cafe,restro,mall,hospital,news,user,query,noti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        cafe=findViewById(R.id.cafecard);
        restro=findViewById(R.id.restrocard);
        mall=findViewById(R.id.mallcard);
        hospital=findViewById(R.id.hospitalcard);
        news=findViewById(R.id.newscard);
        user=findViewById(R.id.usercard);
        query=findViewById(R.id.userQue);
        noti=findViewById(R.id.noticard);
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), cafe.class));
            }
        });

        restro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), restaurant.class));
            }
        });

        mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), mall.class));
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), hospital.class));
            }
        });


        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), news.class));
            }
        });


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), user.class));
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), queryNfeedback.class));
            }
        });
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), notification.class));
            }
        });
    }
}