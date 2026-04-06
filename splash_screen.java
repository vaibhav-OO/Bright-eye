package com.example.bright_eyed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class splash_screen extends AppCompatActivity {

    ImageView img1;
    DocumentReference df;
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        img1 = findViewById(R.id.image1);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        Animation ani= AnimationUtils.loadAnimation(splash_screen.this,R.anim.animation2);
        img1.startAnimation(ani);

        Thread thread=new Thread(){
            @Override
            public void run(){
                try{
                    sleep(2000);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {

                    startActivity(new Intent(splash_screen.this,LoginActivity.class));
                }

            }
        };
        thread.start();
    }
}