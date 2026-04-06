package com.example.bright_eyed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Payment extends AppCompatActivity implements PaymentResultListener
{

    Button pay;
    TextView text,back;
    TextView detailcode;
    String key="";
    TextView name;

    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String userId;
    DocumentReference documentReference;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        name=findViewById(R.id.uname);
        auth= FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        userId=auth.getCurrentUser().getUid();

        documentReference=fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                name.setText(documentSnapshot.getString( "Name"));
            }
        });
        detailcode=findViewById(R.id.coupencode);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Payment.this,userCafe.class));
            }
        });
        img=findViewById(R.id.imageCopy);
        text=findViewById(R.id.textid);
        pay=findViewById(R.id.buttonpay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makepayment();
            }
        });
        Checkout.preload(getApplicationContext());

    }

    private void makepayment() {
            Checkout checkout = new Checkout();

            checkout.setKeyID("rzp_test_ybGHO1qsCAzc1Z");

            checkout.setImage(R.drawable.brighteye);


            final Activity activity = this;


            try {
                JSONObject options = new JSONObject();

                options.put("name", "BRIGHT EYED");
                options.put("description", "Reference No. #123456");
                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
                //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                options.put("theme.color", "#3399cc");
                options.put("currency", "INR");
                options.put("amount", "1000");//pass amount in currency subunits
                options.put("prefill.email", "brighteyed2024@gmail.com");
                options.put("prefill.contact","6352391938");
                JSONObject retryObj = new JSONObject();
                retryObj.put("enabled", true);
                retryObj.put("max_count", 4);
                options.put("retry", retryObj);

                checkout.open(activity, options);

                pay.setVisibility(View.INVISIBLE);
            } catch(Exception e) {
                Log.e("TAG", "Error in starting Razorpay Checkout", e);
            }
        }

    @Override
    public void onPaymentSuccess(String s) {

        text.setText("Your Payment Is Succesfull");

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            detailcode.setText(bundle.getString("code"));
            key=bundle.getString("Key");
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("Copy",detailcode.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(Payment.this,"Tell this Code To Cashier And Get Discout On Payment",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onPaymentError(int i, String s) {
        text.setText("Payment Failed And Cancle :"+s);

    }
}
