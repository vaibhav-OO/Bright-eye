package com.example.bright_eyed;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    TextView txt1,sign1,tv_forgot_password;
    TextInputEditText username,password1;
    Button btn;

    DocumentReference df;
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt1 = findViewById(R.id.txt1);
        Animation ani = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.animation2);
        txt1.startAnimation(ani);
        sign1 = findViewById(R.id.loginRedirectText);
        sign1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        username = findViewById(R.id.login_email);
        password1 = findViewById(R.id.login_password);
        btn = findViewById(R.id.login_button);
        tv_forgot_password = findViewById(R.id.forgot_password);
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();


        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot, null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(LoginActivity.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, pass;
                email = username.getText().toString();
                pass = password1.getText().toString();


                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        mAuth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {

                                        Toast.makeText(LoginActivity.this, "Login Success.", Toast.LENGTH_SHORT).show();
                                        checkUserAccessLevel(authResult.getUser().getUid());
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "Incorrect Password or Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        password1.setError("Empty Fields Not Allow");
                    }

                } else if (email.isEmpty()) {
                    username.setError("Empty Fields Not Allow");
                } else {
                    username.setError("Please Enter Correct Email");
                }
            }

        });
    }
    private void checkUserAccessLevel(String uid) {
        df = mStore.collection("users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Log.d("TAG", "onSuccess: " + documentSnapshot.getData());
                //chech user is Admin Or Normal User.

                if (documentSnapshot.getString("isAdmin") != null) {

                    startActivity(new Intent(getApplicationContext(),admin_home.class));
                    finish();
                }
                if (documentSnapshot.getString("isUser") != null) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //It's check user is Admin Or User and login into it's activity.
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        if (mAuth.getInstance().getCurrentUser() != null) {

            df = mStore.collection("users").document(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());

            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                    if (documentSnapshot.getString("isAdmin") != null) {

                        startActivity(new Intent(getApplicationContext(), admin_home.class));
                        finish();
                    }
                    if (documentSnapshot.getString("isUser") != null) {

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    mAuth.signOut();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            });
        }
    }
}

