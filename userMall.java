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

public class userMall extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;

    MyAdaptermall adaptermall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mall);

        recyclerView = findViewById(R.id.recyclerMall);

        searchView = findViewById(R.id.mallSearch);

        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(userMall.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(userMall.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout1);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adaptermall = new MyAdaptermall(userMall.this, dataList);
        recyclerView.setAdapter(adaptermall);

        databaseReference = FirebaseDatabase.getInstance().getReference("Mall");
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
                adaptermall.notifyDataSetChanged();
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
            if (dataClass.getDatamallName().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataClass);
            }
        }
        adaptermall.searchDataList(searchList);
    }
}
