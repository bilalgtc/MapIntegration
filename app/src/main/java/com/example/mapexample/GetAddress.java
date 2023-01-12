package com.example.mapexample;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetAddress extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap Map;
    TextView user_address;
    Button button;
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE= 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address);

        button = findViewById(R.id.get_btn);
        user_address= findViewById(R.id.location_txt);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map3);
        mapFragment.getMapAsync(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }


        });



    }


    private void getLocation() {
        if (ContextCompat.checkSelfPermission(GetAddress.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location!=null){
                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        try {
                            List<Address> address=geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            user_address.setText(address.get(0).getAddressLine(0));
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            Map.addMarker(new MarkerOptions().position(latLng));
                            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(GetAddress.this, "Something's Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            ActivityCompat.requestPermissions(GetAddress.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getLocation();
            }else {
                Toast.makeText(this, "Please allow the permissions", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Map = googleMap;

    }
}