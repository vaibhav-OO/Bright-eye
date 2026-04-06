package com.example.bright_eyed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userRestro extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    ImageSlider imageSlider;

    MyAdapterrestro adapterrestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_restro);

        imageSlider=findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList=new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.restrooffer1,null));
        imageList.add(new SlideModel(R.drawable.restrooffer2,null));
        imageList.add(new SlideModel(R.drawable.restrooffer3,null));

        imageSlider.setImageList(imageList);

        recyclerView = findViewById(R.id.recyclerRestro);

        searchView = findViewById(R.id.restroSearch);

        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(userRestro.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(userRestro.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout1);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapterrestro = new MyAdapterrestro(userRestro.this, dataList);
        recyclerView.setAdapter(adapterrestro);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurant");
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
                adapterrestro.notifyDataSetChanged();
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
            if (dataClass.getRestroName().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataClass);
            }
        }
        adapterrestro.searchDataList(searchList);
    }
}

