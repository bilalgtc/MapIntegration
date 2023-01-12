package com.example.mapexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button map_btn,search_btn,marker_btn,live_btn,route_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map_btn = findViewById(R.id.btn1);
        search_btn = findViewById(R.id.btn2);
        marker_btn = findViewById(R.id.btn3);
        live_btn = findViewById(R.id.btn4);
        route_btn = findViewById(R.id.btn5);


        map_btn.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        marker_btn.setOnClickListener(this);
        live_btn.setOnClickListener(this);
        route_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn1:

               startActivity(new Intent(getApplicationContext(),MapsActivity.class));
               break;

           case R.id.btn2:
               startActivity(new Intent(getApplicationContext(),SearchLocation.class));
               break;

           case R.id.btn3:
               startActivity(new Intent(getApplicationContext(),CustomMarker.class));
               break;

           case R.id.btn4:
               startActivity(new Intent(getApplicationContext(),GetAddress.class));
               break;

           case R.id.btn5:
               startActivity(new Intent(getApplicationContext(),Route.class));
               break;

       }


        }
    }
