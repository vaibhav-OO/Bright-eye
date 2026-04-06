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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddCoupenInCafe extends AppCompatActivity {
    FloatingActionButton fab1;

    RecyclerView recyclercoupen;
    List<DataCafeClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;

    MyAdapterCC adapterCC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupen_in_cafe);

        recyclercoupen=findViewById(R.id.recyclerViewcoupen);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(AddCoupenInCafe.this,1);
        recyclercoupen.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder=new AlertDialog.Builder(AddCoupenInCafe.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout1);
        AlertDialog dialog=builder.create();
        dialog.show();

        dataList=new ArrayList<>();

        adapterCC=new MyAdapterCC(AddCoupenInCafe.this,dataList);
        recyclercoupen.setAdapter(adapterCC);

        databaseReference= FirebaseDatabase.getInstance().getReference("CoupenCafe");
        dialog.show();

        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataCafeClass dataClass=itemSnapshot.getValue(DataCafeClass.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    dataList.add(dataClass);
                }
                adapterCC.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                dialog.dismiss();
            }
        });
        fab1=findViewById(R.id.fab);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), uploadcoupencafe.class));
            }
        });
    }
}