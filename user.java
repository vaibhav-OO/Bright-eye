package com.example.bright_eyed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class user extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Userbright> userbrightArrayList;
    MyAdapterUser myAdapterUser;
    FirebaseFirestore db;
    Button logout;
    FirebaseAuth dauth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fatching Data...");
        progressDialog.show();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();
        userbrightArrayList=new ArrayList<Userbright>();
        myAdapterUser=new MyAdapterUser(user.this,userbrightArrayList);

        recyclerView.setAdapter(myAdapterUser);

        EventChangeListener();
        dauth = FirebaseAuth.getInstance();
         logout=findViewById(R.id.adminlogout);
         logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        dauth.signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
        }
        });
    }

    private void EventChangeListener() {
        db.collection("users").orderBy("Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("FireStore Error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){

                            if(dc.getType()==DocumentChange.Type.ADDED){
                                userbrightArrayList.add(dc.getDocument().toObject(Userbright.class));
                            }
                            myAdapterUser.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }
}