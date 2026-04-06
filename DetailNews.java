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

public class DetailNews extends AppCompatActivity {

    TextView detailName,detailAdd,detailContact;
    ImageView detailImage;
    FloatingActionButton deleteButton,editButton;

    String key="";
    String imageUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        detailImage=findViewById(R.id.detailImage);
        detailName=findViewById(R.id.detailName);
        detailAdd=findViewById(R.id.detailAdd);


        deleteButton=findViewById(R.id.deleteButton);
        editButton=findViewById(R.id.editButton);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            detailAdd.setText(bundle.getString("Address"));
            detailName.setText(bundle.getString("Name"));
            key=bundle.getString("Key");
            imageUrl=bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("News");
                FirebaseStorage storage=FirebaseStorage.getInstance();

                StorageReference storageReference=storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailNews.this,"Delete",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),news.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailNews.this, UpdateNews.class)
                        .putExtra("Name", detailName.getText().toString())
                        .putExtra("Address", detailAdd.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}
