package com.example.bright_eyed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

public class userFeedback extends AppCompatActivity {
    RecyclerView recyclerfeedback;
    List<DataFeedback> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    MyAdapterFeedback adapterFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        recyclerfeedback=findViewById(R.id.recyclerFeedback);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(userFeedback.this,1);
        recyclerfeedback.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder=new AlertDialog.Builder(userFeedback.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout1);
        AlertDialog dialog=builder.create();
        dialog.show();

        dataList=new ArrayList<>();

        adapterFeedback=new MyAdapterFeedback(userFeedback.this,dataList);
        recyclerfeedback.setAdapter(adapterFeedback);

        databaseReference= FirebaseDatabase.getInstance().getReference("UserFeedback");
        dialog.show();

        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataFeedback dataClass=itemSnapshot.getValue(DataFeedback.class);
                    dataList.add(dataClass);
                }
                adapterFeedback.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                dialog.dismiss();
            }
        });
    }
}