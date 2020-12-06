package com.example.highrestau;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {

//    private TextView Login;

    EditText mfullName,mEmail,mPassword,mPhone,mDept,mAddress;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fstore = FirebaseFirestore.getInstance();

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mfullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mDept = findViewById(R.id.dept);
        mAddress = findViewById(R.id.Address);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.create1);

//        Login = (TextView) findViewById(R.id.create1);


        fAuth = FirebaseAuth.getInstance();//getting instance of database under database
        progressBar = findViewById(R.id.progressBar);
        if (fAuth.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = mfullName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String address = mAddress.getText().toString().trim();
                String dept = mDept.getText().toString().trim();
                 if (TextUtils.isEmpty(fullname)){
                     mfullName.setError("Fullname is required");
                     return;
                 }

                 if (TextUtils.isEmpty(email)){
                     mEmail.setError("Email is required");
                     return;

                 }
                 if (TextUtils.isEmpty(password)){
                     mPassword.setError("Password is required");
                     return;
                 }
                 if (password.length()<8){
                     mPassword.setError("Password Must be >= 8 characters");
                     return;
                 }
                 if (TextUtils.isEmpty(phone)){
                     mPhone.setError("Phone is required");
                     return;

                 }
                 if (TextUtils.isEmpty(address)){
                     mAddress.setError("Address is required");
                     return;
                 }
                 if (TextUtils.isEmpty(dept)){
                     mDept.setError("Dept is required");
                     return;
                 }
                 progressBar.setVisibility(View.VISIBLE);
                 //Register the user in firebase
                 fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             Toast.makeText(Register.this,"user Created.", Toast.LENGTH_SHORT).show();
                             userID = fAuth.getCurrentUser().getUid();
                             Map<String,Object> user = new HashMap<>();
                             DocumentReference documentReference = fstore.collection("users").document(userID);
                             user.put("FullName", fullname);
                             user.put("Email", email);
                             user.put("phone", phone);
                             user.put("Dept", dept);
                             user.put("Address", address);
                             documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {

                                 private static final String TAG = "";

                                 @Override
                                 public void onSuccess(Void aVoid) {

                                     Log.d(TAG, "onSuccess: Profile is successful upload "+ userID );
                     
                                 }
                             });

                             startActivity(new Intent(getApplicationContext(),MainActivity.class));
                         } else {
                             Toast.makeText(Register.this, "Error is occur" + task.getException().getMessage(), Toast.LENGTH_SHORT);
                             progressBar.setVisibility(View.GONE);
                         }
                     }
                 });
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));

            }
        });
    }
}