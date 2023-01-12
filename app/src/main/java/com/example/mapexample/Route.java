package com.example.mapexample;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Route extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap map;
    private GoogleMap temp;
    EditText start_ed,end_ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map6);
        mapFragment.getMapAsync(this);

        start_ed = findViewById(R.id.start);
        end_ed = findViewById(R.id.end);
        Button button = findViewById(R.id.btn_submit);

        button.setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
//
//            MarkerOptions markerOptions = new MarkerOptions();
//                markerOptions.position(latLng);
//                markerOptions.title(latLng.latitude +":"+latLng.longitude);
////                map.clear();
//                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,2));
//                map.addMarker(markerOptions);
//
//            LatLng latLng = new LatLng(startposi.);
//            LatLng randon = new LatLng(-324, 101);
//        map.addMarker(new
//
//            MarkerOptions().
//
//            position(sydney).
//
//            title("Sydney"));
//        map.addMarker(new
//
//            MarkerOptions().
//
//            position(randon).
//
//            title("Randon"));
//        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        map.moveCamera(CameraUpdateFactory.newLatLng(randon));
//
//            PolylineOptions polylineOptions = new PolylineOptions().add(new LatLng(-34, 151), new LatLng(-324, 101));
//        map.addPolyline(polylineOptions);
    }


    @Override
    public void onClick(View v) {
        String startposi = start_ed.getText().toString();
        String endposi = end_ed.getText().toString();
//        RoadManager roadManager = new OSRMRoadManager();

        if(startposi==null && endposi ==null){
            Toast.makeText(this, "Enter data", Toast.LENGTH_SHORT).show();
        }else {
            Geocoder geocoder = new Geocoder(Route.this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocationName(startposi, 1);
                List<Address> addressList = geocoder.getFromLocationName(endposi, 1);
                if(addresses.size()>0 && addressList.size()>0){

                    LatLng latLng= new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                    LatLng latLng1 =  new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());

                    map.addMarker(new MarkerOptions().position(latLng).title(startposi));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                    map.addMarker(new MarkerOptions().position(latLng1).title(endposi));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 10));

//                    Polyline roadOverlay = RoadManager.buildRoadOverlay(road, Color.RED, 8, context);
//                    map.getOverlays().add(roadOverlay);

                    PolylineOptions polylineOptions = new PolylineOptions().add(new LatLng(latLng.latitude,latLng.longitude), new LatLng(latLng1.latitude,latLng1.longitude)).geodesic(true);
                    map.addPolyline(polylineOptions);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

//                List<Address> addresses = null;
//
//                if (startposi != null || !startposi.equals("")) {
//                    Geocoder geocoder = new Geocoder(Route.this);
//                    try {
//                        addresses = geocoder.getFromLocationName(start_ed.getText().toString(), 1);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//            Address address = addresses.get(0);
//            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//            map.addMarker(new MarkerOptions().position(latLng).title(startposi));
//            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }
}