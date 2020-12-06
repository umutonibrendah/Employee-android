package com.example.highrestau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView login = (TextView) findViewById(R.id.create2);
        TextView  create = (TextView) findViewById(R.id.create1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tologi = new Intent(getApplicationContext(), Login.class);
                startActivity(tologi);

            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  toregi = new Intent(getApplicationContext(),Register.class);
                startActivity(toregi);

            }
        });


    }


}