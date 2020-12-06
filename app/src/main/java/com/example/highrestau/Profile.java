package com.example.highrestau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {

    TextView fullname,Email,phone,dept,address;
    Button mLogoutBtn;
    Button mEditBtn;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    protected void onCreate(@Nullable Bundle savedInstanceState, int documentSnapshot) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullname = findViewById(R.id.fullname);
        Email = findViewById(R.id.Email);
        phone = findViewById(R.id.phone);
        dept = findViewById(R.id.dept);
        address = findViewById(R.id.Address);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("user").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                fullname.setText(documentSnapshot.getString("FullName"));
                Email.setText(documentSnapshot.getString("Email"));
                phone.setText(documentSnapshot.getString("phone"));
                dept.setText(documentSnapshot.getString("Dept"));
                address.setText(documentSnapshot.getString("Address"));


            }
        });

        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}

