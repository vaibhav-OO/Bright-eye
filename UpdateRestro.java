package com.example.bright_eyed;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateRestro extends AppCompatActivity {

    ImageView updateImage;
    Button updateButton;
    EditText updateName, updateAdd, updateCont,updateTime,updateRating;
    String name, address, contact,time,rating;
    String imageUrl;
    String key, oldImageURL;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_restro);

        updateButton = findViewById(R.id.updateButton);
        updateName = findViewById(R.id.updateRestroName);
        updateImage = findViewById(R.id.updateImage);
        updateAdd = findViewById(R.id.updateresAdd);
        updateCont = findViewById(R.id.updateContact);
        updateTime=findViewById(R.id.updateTime);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            updateImage.setImageURI(uri);
                        } else {
                            Toast.makeText(UpdateRestro.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Glide.with(UpdateRestro.this).load(bundle.getString("Image")).into(updateImage);
            updateName.setText(bundle.getString("Name"));
            updateAdd.setText(bundle.getString("Address"));
            updateCont.setText(bundle.getString("Contact"));
            updateTime.setText(bundle.getString("Timing"));
            key = bundle.getString("Key");
            oldImageURL = bundle.getString("Image");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurant").child(key);

        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(UpdateRestro.this, restaurant.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){
        storageReference = FirebaseStorage.getInstance().getReference().child("Restaurant Images").child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateRestro.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                updateData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
    public void updateData(){
        name = updateName.getText().toString().trim();
        address = updateAdd.getText().toString().trim();
        contact = updateCont.getText().toString().trim();
        time = updateTime.getText().toString().trim();

        DataClass dataClass = new DataClass(name, address, contact,time, imageUrl);

        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(UpdateRestro.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateRestro.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}