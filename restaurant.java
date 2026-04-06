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
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class restaurant extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerrestro;
    List<DataClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    TextView txt;
    SearchView searchView;
    MyAdapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        recyclerrestro=findViewById(R.id.recyclerRestro);


        searchView=findViewById(R.id.search2);
        searchView.clearFocus();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(restaurant.this,1);
        recyclerrestro.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder=new AlertDialog.Builder(restaurant.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout1);
        AlertDialog dialog=builder.create();
        dialog.show();

        dataList=new ArrayList<>();

        adapter2=new MyAdapter2(restaurant.this,dataList);
        recyclerrestro.setAdapter(adapter2);

        databaseReference= FirebaseDatabase.getInstance().getReference("Restaurant");
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
                adapter2.notifyDataSetChanged();
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

        fab=findViewById(R.id.fabrestro);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UploadRestro.class));
            }
        });

    }
    public void searchList(String text){
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass: dataList){
            if (dataClass.getRestroName().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter2.searchDataList(searchList);
    }
}
