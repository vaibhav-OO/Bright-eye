package com.example.bright_eyed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Userinfo extends AppCompatActivity {

    TextView name,mail,add,mbno;

    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String userId;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        name=findViewById(R.id.userName);
        mail=findViewById(R.id.userMail);
        add=findViewById(R.id.userAdd);
        mbno=findViewById(R.id.userMbno);
        auth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        userId=auth.getCurrentUser().getUid();

        documentReference=fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                name.setText(documentSnapshot.getString( "Name"));
                mail.setText(documentSnapshot.getString( "Email"));
                add.setText(documentSnapshot.getString( "Address"));
                mbno.setText(documentSnapshot.getString( "Number"));
            }
        });
    }
}