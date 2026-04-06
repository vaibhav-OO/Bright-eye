package com.example.bright_eyed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class user_detail_cafe extends AppCompatActivity {

    TextView detailName,detailAdd,detailContact,detailTime,detailRating;
    RecyclerView recyclercoupen;
    List<DataCafeClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;

    MyAdapterUCC adapterUCC;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_cafe);

        recyclercoupen=findViewById(R.id.recyclerViewcoupen1);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(user_detail_cafe.this,1);
        recyclercoupen.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder=new AlertDialog.Builder(user_detail_cafe.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout1);
        AlertDialog dialog=builder.create();
        dialog.show();

        dataList=new ArrayList<>();

        adapterUCC=new MyAdapterUCC(user_detail_cafe.this,dataList);
        recyclercoupen.setAdapter(adapterUCC);

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
                adapterUCC.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                dialog.dismiss();
            }
        });

        detailImage=findViewById(R.id.detailImage);
        detailName=findViewById(R.id.detailName);
        detailAdd=findViewById(R.id.detailAdd);
        detailContact=findViewById(R.id.detailContact);
        detailTime=findViewById(R.id.detailTime);
        detailRating=findViewById(R.id.detailRating);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            detailAdd.setText(bundle.getString("Address"));
            detailName.setText(bundle.getString("Name"));
            detailContact.setText(bundle.getString("Contact"));
            detailTime.setText(bundle.getString("Timing"));
            detailRating.setText(bundle.getString("Rating"));

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }
}