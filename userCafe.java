package com.example.bright_eyed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userCafe extends AppCompatActivity {


    RecyclerView recyclerView;
    List<DataClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    SearchView searchView;
    ImageSlider imageSlider;

    MyAdaptercafe adaptercafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cafe);


        imageSlider=findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList=new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.coffer1,null));
        imageList.add(new SlideModel(R.drawable.coffer2,null));
        imageList.add(new SlideModel(R.drawable.coffer3,null));
        imageList.add(new SlideModel(R.drawable.coffer4,null));

        imageSlider.setImageList(imageList);

        recyclerView = findViewById(R.id.recyclerView);

        searchView = findViewById(R.id.cafesearch);

        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(userCafe.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(userCafe.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout1);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adaptercafe = new MyAdaptercafe(userCafe.this, dataList);
        recyclerView.setAdapter(adaptercafe);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cafe");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    dataList.add(dataClass);
                }
                adaptercafe.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
    }
        public void searchList (String text){
            ArrayList<DataClass> searchList = new ArrayList<>();
            for (DataClass dataClass : dataList) {
                if (dataClass.getDataName().toLowerCase().contains(text.toLowerCase())) {
                    searchList.add(dataClass);
                }
            }
            adaptercafe.searchDataList(searchList);
        }
    }
