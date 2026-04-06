package com.example.bright_eyed;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword,signupName,signupAdd,signupMbno;
    private Button signupButton;
    private TextView loginRedirectText,txt1;
    FirebaseFirestore fstore;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txt1 = findViewById(R.id.txt2);
        Animation ani = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.animation2);
        txt1.startAnimation(ani);


        auth = FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        FirebaseApp.initializeApp(this);
        if (FirebaseApp.getApps(this).size() > 0) {
            Log.d("Firebase_TEST", "Firebase connect successfully.");
        }else {
            Log.d("Firebase_TEST", "Firebase NOT Connect");
        }


        signupName=findViewById(R.id.signup_name);
        signupAdd=findViewById(R.id.signup_add);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupMbno = findViewById(R.id.signup_mbno);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user1 = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                String name1 = signupName.getText().toString().trim();
                String add1 = signupAdd.getText().toString().trim();
                String mbno1 = signupMbno.getText().toString().trim();

                if (user1.isEmpty()){
                    signupEmail.setError("Email cannot be empty");
                }
                if (pass.isEmpty()){
                    signupPassword.setError("Password cannot be empty");
                }
                if (name1.isEmpty()){
                    signupName.setError("Name cannot be empty");
                }
                if (add1.isEmpty()){
                    signupAdd.setError("Address cannot be empty");
                }
                if (mbno1.isEmpty()){
                    signupMbno.setError("Mobile cannot be empty");
                }else{
                    auth.createUserWithEmailAndPassword(user1, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(SignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();



                                userID = auth.getCurrentUser().getUid();


                                DocumentReference documentReference = fstore.collection("users").document(userID);

                                Map<String,Object> user=new HashMap<>();
                                user.put("Email",user1);
                                user.put("Password",pass);
                                user.put("Name",name1);
                                user.put("Address",add1);
                                user.put("Number",mbno1);
                                user.put("isUser","1");

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        Log.d(TAG,"on Success:User profile is Created" + userID);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG,"on Failure:" + e.toString());
                                    }
                                });
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

    }
}