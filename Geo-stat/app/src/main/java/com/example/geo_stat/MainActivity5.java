package com.example.geo_stat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity5 extends AppCompatActivity {
    Button weathersrch, livelocation,askai,historical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        weathersrch=findViewById(R.id.weathersrch);
        livelocation=findViewById(R.id.livelocation);
        askai=findViewById(R.id.askai);
        historical=findViewById(R.id.history);
       weathersrch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity5.this,MainActivity4.class);
               startActivity(intent);
           }
       });
        livelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity5.this, MainActivity6.class);
                startActivity(intent);
            }
        });

        askai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity5.this, MainActivity8.class);
                startActivity(intent);
            }
        });
        historical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity5.this, weatherlist.class);
                startActivity(intent);
            }
        });

    }
}