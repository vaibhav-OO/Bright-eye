package com.example.bright_eyed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userHospital extends AppCompatActivity {
    RecyclerView recyclerhospital;
    List<DataClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    SearchView searchView;

    MyAdapterhospital adapterhospital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hospital);
        recyclerhospital=findViewById(R.id.recyclerHospital);

        searchView=findViewById(R.id.hospitalSearch);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(userHospital.this,1);
        recyclerhospital.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder=new AlertDialog.Builder(userHospital.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout1);
        AlertDialog dialog=builder.create();
        dialog.show();

        dataList=new ArrayList<>();

        adapterhospital=new MyAdapterhospital(userHospital.this,dataList);
        recyclerhospital.setAdapter(adapterhospital);

        databaseReference= FirebaseDatabase.getInstance().getReference("Hospital");
        dialog.show();

        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass=itemSnapshot.getValue(DataClass.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    dataList.add(dataClass);
                }
                adapterhospital.notifyDataSetChanged();
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
    public void searchList(String text){
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass: dataList){
            if (dataClass.getHospitalName().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapterhospital.searchDataList(searchList);
    }
}