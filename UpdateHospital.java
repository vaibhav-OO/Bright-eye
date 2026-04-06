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

public class UpdateHospital extends AppCompatActivity {

    ImageView updateImage;
    Button updateButton;
    EditText updateName, updateAdd, updateCont,updateTime,updateRating,updateSector;
    String name, address, contact,time,rating,sector;
    String imageUrl;
    String key, oldImageURL;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hospital);

        updateButton = findViewById(R.id.updateButton);
        updateName = findViewById(R.id.updateHospiName);
        updateImage = findViewById(R.id.updateImage);
        updateAdd = findViewById(R.id.updateAdd);
        updateCont = findViewById(R.id.updateContact);
        updateTime=findViewById(R.id.updateTime);
        updateRating=findViewById(R.id.updateRating);
        updateSector=findViewById(R.id.updateSector);

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
                            Toast.makeText(UpdateHospital.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Glide.with(UpdateHospital.this).load(bundle.getString("Image")).into(updateImage);
            updateName.setText(bundle.getString("Name"));
            updateAdd.setText(bundle.getString("Address"));
            updateCont.setText(bundle.getString("Contact"));
            updateTime.setText(bundle.getString("Timing"));
            updateRating.setText(bundle.getString("Rating"));
            updateSector.setText(bundle.getString("Sector"));
            key = bundle.getString("Key");
            oldImageURL = bundle.getString("Image");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Hospital").child(key);

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
                Intent intent = new Intent(UpdateHospital.this, hospital.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){
        storageReference = FirebaseStorage.getInstance().getReference().child("Hospital Images").child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateHospital.this);
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
        rating = updateRating.getText().toString().trim();
        sector = updateSector.getText().toString();

        DataClass dataClass = new DataClass(name, address, contact,time,rating,sector, imageUrl);

        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(UpdateHospital.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateHospital.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}