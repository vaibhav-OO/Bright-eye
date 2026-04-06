package com.example.bright_eyed;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
    MainActivity mainActivity;

    FirebaseAuth mAuth;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        Button b1=view.findViewById(R.id.signout1);
        mainActivity=(MainActivity)getActivity();
        mAuth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                Intent i=new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        });

        TextView feedback=view.findViewById(R.id.feedbacktxt);
        mainActivity=(MainActivity)getActivity();
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),Feedback.class);
                startActivity(i);
            }
        });

        TextView udata=view.findViewById(R.id.userdata);
        mainActivity=(MainActivity)getActivity();
        udata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Userinfo.class);
                startActivity(i);
            }
        });
        return view;
    }
}