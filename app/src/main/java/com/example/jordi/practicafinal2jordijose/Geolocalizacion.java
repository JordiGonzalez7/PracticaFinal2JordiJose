package com.example.jordi.practicafinal2jordijose;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Geolocalizacion extends AppCompatActivity implements LocationListener {

    private Button btn3S, btn1, btn2, btn4;
    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalizacion);


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3S = (Button) findViewById(R.id.botonAtras);
        btn4 = (Button) findViewById(R.id.btn4);


        LocationManager LoMa = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        location = LoMa.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        LoMa.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 1, this);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (location == null) {
                    Toast.makeText(getApplicationContext(), " Esperando a recibir las coordenadas", Toast.LENGTH_LONG).show();
                } else {
                    String txt = " Posicion actual:\n " + " Latitud = " + location.getLatitude() + "\n" + " Longitud = " + location.getLongitude();
                    Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
                }

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });


        btn3S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    //Metodos del location listener


    @Override
    public void onLocationChanged(Location location) {
        String txt = " Posicion actual:\n " + " Latitud = " + location.getLatitude() + "\n" + " Longitud = " + location.getLongitude();
        Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        String mensage = "";
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                mensage = ("GPS STATUS: Fuera de servicio");
                Toast.makeText(getApplicationContext(), mensage, Toast.LENGTH_LONG).show();
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                mensage = ("GPS STATUS: Temporalmente desactivado");
                Toast.makeText(getApplicationContext(), mensage, Toast.LENGTH_LONG).show();
                break;
            case LocationProvider.AVAILABLE:
                mensage = ("GPS STATUS: Disponible");
                Toast.makeText(getApplicationContext(), mensage, Toast.LENGTH_LONG).show();
                break;


        }

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getApplicationContext(), "El gps ha sido activado.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String provider) {

        Toast.makeText(getApplicationContext(), "El gps ha sido desactivado.", Toast.LENGTH_LONG).show();
    }
}
