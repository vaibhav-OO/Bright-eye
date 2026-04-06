package com.example.bright_eyed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userContactUs extends AppCompatActivity {

    RecyclerView recyclerquery;
    List<DataQuery> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    MyAdapterQuery adapterQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contact_us);

        recyclerquery=findViewById(R.id.recyclerQuery);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(userContactUs.this,1);
        recyclerquery.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder=new AlertDialog.Builder(userContactUs.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout1);
        AlertDialog dialog=builder.create();
        dialog.show();

        dataList=new ArrayList<>();

        adapterQuery=new MyAdapterQuery(userContactUs.this,dataList);
        recyclerquery.setAdapter(adapterQuery);

        databaseReference= FirebaseDatabase.getInstance().getReference("UserQuery");
        dialog.show();

        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataQuery dataClass=itemSnapshot.getValue(DataQuery.class);
                    dataList.add(dataClass);
                }
                adapterQuery.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                dialog.dismiss();
            }
        });
    }
}