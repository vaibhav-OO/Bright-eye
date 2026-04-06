package com.example.bright_eyed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    MainActivity mainActivity;

    ImageView help;
    CardView cafe,restro,mall,hospital,news;
    ImageSlider imageSlider;

    FirebaseAuth mAuth;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);


     /**Button b1=view.findViewById(R.id.signout1);
        mainActivity=(MainActivity)getActivity();
        mAuth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                Intent i=new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        });**/
        help=view.findViewById(R.id.imagehelp);
        mainActivity=(MainActivity) getActivity();
        mAuth=FirebaseAuth.getInstance();
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),contactUs.class);
                startActivity(i);
            }
        });

     cafe=view.findViewById(R.id.cafecard);
     mainActivity=(MainActivity) getActivity();
     mAuth=FirebaseAuth.getInstance();
     cafe.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i=new Intent(getActivity(),userCafe.class);
             startActivity(i);
         }
     });

        restro=view.findViewById(R.id.restaurantcard);
        mainActivity=(MainActivity) getActivity();
        mAuth=FirebaseAuth.getInstance();
        restro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),userRestro.class);
                startActivity(i);
            }
        });

        mall=view.findViewById(R.id.mallcard);
        mainActivity=(MainActivity) getActivity();
        mAuth=FirebaseAuth.getInstance();
        mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),userMall.class);
                startActivity(i);
            }
        });

        hospital=view.findViewById(R.id.hospitalcard);
        mainActivity=(MainActivity) getActivity();
        mAuth=FirebaseAuth.getInstance();
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),userHospital.class);
                startActivity(i);
            }
        });

        news=view.findViewById(R.id.newscard);
        mainActivity=(MainActivity) getActivity();
        mAuth=FirebaseAuth.getInstance();
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),userNews.class);
                startActivity(i);
            }
        });

        imageSlider=view.findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList=new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.two,null));
        imageList.add(new SlideModel(R.drawable.one1,null));
        imageList.add(new SlideModel(R.drawable.three,null));
        imageList.add(new SlideModel(R.drawable.four,null));

        imageSlider.setImageList(imageList);
        return view;
    }
}