package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailHospital extends AppCompatActivity {

    TextView detailName,detailAdd,detailContact,detailTime,detailRating,detailSector;
    ImageView detailImage;
    FloatingActionButton deleteButton,editButton;

    String key="";
    String imageUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);

        detailImage=findViewById(R.id.detailImage);
        detailName=findViewById(R.id.detailName);
        detailAdd=findViewById(R.id.detailAdd);
        detailContact=findViewById(R.id.detailContact);
        detailTime=findViewById(R.id.detailTime);
        detailRating=findViewById(R.id.detailRating);
        detailSector=findViewById(R.id.detailSector);

        deleteButton=findViewById(R.id.deleteButton);
        editButton=findViewById(R.id.editButton);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            detailAdd.setText(bundle.getString("Address"));
            detailName.setText(bundle.getString("Name"));
            detailContact.setText(bundle.getString("Contact"));
            detailTime.setText(bundle.getString("Timing"));
            detailRating.setText(bundle.getString("Rating"));
            detailSector.setText(bundle.getString("Sector"));
            key=bundle.getString("Key");
            imageUrl=bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Hospital");
                FirebaseStorage storage=FirebaseStorage.getInstance();

                StorageReference storageReference=storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailHospital.this,"Delete",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),hospital.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailHospital.this, UpdateHospital.class)
                        .putExtra("Name", detailName.getText().toString())
                        .putExtra("Address", detailAdd.getText().toString())
                        .putExtra("Contact", detailContact.getText().toString())
                        .putExtra("Timing", detailTime.getText().toString())
                        .putExtra("Rating", detailRating.getText().toString())
                        .putExtra("Sector", detailSector.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}