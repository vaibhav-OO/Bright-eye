package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //private int selectTab=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");



    };
}


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout homeLayout=findViewById(R.id.homeLayout);
        final LinearLayout likeLayout=findViewById(R.id.likeLayout);
        final LinearLayout notifiLayout=findViewById(R.id.notificationLayout);
        final LinearLayout personLayout=findViewById(R.id.personLayout);

        final ImageView homeImage=findViewById(R.id.homeImage);
        final ImageView likeImage=findViewById(R.id.likeImage);
        final ImageView notifiImage=findViewById(R.id.notifiImage);
        final ImageView personImage=findViewById(R.id.personImage);

        final TextView homeTxt=findViewById(R.id.homeTxt);
        final TextView likeTxt=findViewById(R.id.likeTxt);
        final TextView notifiTxt=findViewById(R.id.notifiTxt);
        final TextView personTxt=findViewById(R.id.personTxt);

        getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                                .replace(R.id.fragmentContainer,HomeFragment.class,null)
                                        .commit();
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectTab!=1){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,HomeFragment.class,null)
                            .commit();

                    likeTxt.setVisibility(View.GONE);
                    notifiTxt.setVisibility(View.GONE);
                    personTxt.setVisibility(View.GONE);

                    likeImage.setImageResource(R.drawable.like_icon);
                    notifiImage.setImageResource(R.drawable.notifi_icon);
                    personImage.setImageResource(R.drawable.baseline_settings_24);

                    likeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notifiLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    personLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeTxt.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.home_select_icon);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    ScaleAnimation scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    selectTab=1;
                }
            }
        });

        likeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectTab!=2){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,LikeFragment.class,null)
                            .commit();

                    homeTxt.setVisibility(View.GONE);
                    notifiTxt.setVisibility(View.GONE);
                    personTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home_icon);
                    notifiImage.setImageResource(R.drawable.notifi_icon);
                    personImage.setImageResource(R.drawable.baseline_settings_24);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notifiLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    personLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    likeTxt.setVisibility(View.VISIBLE);
                    likeImage.setImageResource(R.drawable.like_select_icon);
                    likeLayout.setBackgroundResource(R.drawable.round_back_like_100);

                    ScaleAnimation scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    likeLayout.startAnimation(scaleAnimation);

                    selectTab=2;
                }
            }
        });

        notifiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectTab!=3){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,NotificationFragment.class,null)
                            .commit();

                    homeTxt.setVisibility(View.GONE);
                    likeTxt.setVisibility(View.GONE);
                    personTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home_icon);
                    likeImage.setImageResource(R.drawable.like_icon);
                    personImage.setImageResource(R.drawable.baseline_settings_24);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    likeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    personLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    notifiTxt.setVisibility(View.VISIBLE);
                    notifiImage.setImageResource(R.drawable.notifi_select_icon);
                    notifiLayout.setBackgroundResource(R.drawable.round_back_notifi_100);

                    ScaleAnimation scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    notifiLayout.startAnimation(scaleAnimation);

                    selectTab=3;
                }
            }
        });

        personLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectTab!=4){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,ProfileFragment.class,null)
                            .commit();
                    homeTxt.setVisibility(View.GONE);
                    likeTxt.setVisibility(View.GONE);
                    notifiTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home_icon);
                    likeImage.setImageResource(R.drawable.like_icon);
                    notifiImage.setImageResource(R.drawable.notifi_icon);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    likeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notifiLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    personTxt.setVisibility(View.VISIBLE);
                    personImage.setImageResource(R.drawable.person_select_icon);
                    personLayout.setBackgroundResource(R.drawable.round_back_person_100);

                    ScaleAnimation scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    personLayout.startAnimation(scaleAnimation);

                    selectTab=4;
                }
            }
        });
    }
}
*/