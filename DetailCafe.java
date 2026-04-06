package com.example.bright_eyed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

public class DetailCafe extends AppCompatActivity {

    TextView detailName,detailAdd,detailContact,detailTime,detailRating;
    ImageView detailImage;
    FloatingActionButton deleteButton,editButton;

    String key="";
    String imageUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cafe);

        detailImage=findViewById(R.id.detailImage);
        detailName=findViewById(R.id.detailName);
        detailAdd=findViewById(R.id.detailAdd);
        detailContact=findViewById(R.id.detailContact);
        detailTime=findViewById(R.id.detailTime);
        detailRating=findViewById(R.id.detailRating);

        deleteButton=findViewById(R.id.deleteButton);
        editButton=findViewById(R.id.editButton);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            detailAdd.setText(bundle.getString("Address"));
            detailName.setText(bundle.getString("Name"));
            detailContact.setText(bundle.getString("Contact"));
            detailTime.setText(bundle.getString("Timing"));
            detailRating.setText(bundle.getString("Rating"));
            key=bundle.getString("Key");
            imageUrl=bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Cafe");
                FirebaseStorage storage=FirebaseStorage.getInstance();

                StorageReference storageReference=storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailCafe.this,"Delete",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),cafe.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailCafe.this, UpdateCafe.class)
                        .putExtra("Name", detailName.getText().toString())
                        .putExtra("Address", detailAdd.getText().toString())
                        .putExtra("Contact", detailContact.getText().toString())
                        .putExtra("Timing", detailTime.getText().toString())
                        .putExtra("Rating", detailRating.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}