package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class imageslider extends AppCompatActivity {

    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageslider);

        imageSlider=findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList=new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.one,null));
        imageList.add(new SlideModel(R.drawable.two,null));
        imageList.add(new SlideModel(R.drawable.three,null));
        imageList.add(new SlideModel(R.drawable.four,null));

        imageSlider.setImageList(imageList);
    }
}