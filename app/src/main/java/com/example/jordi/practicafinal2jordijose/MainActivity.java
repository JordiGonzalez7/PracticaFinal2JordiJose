package com.example.jordi.practicafinal2jordijose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn1,btn2,btn3,btn4,btn5;

//en esta activity tenemos todos los botones que van a cada una de la actividades, estos son imageButtons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (ImageButton) findViewById(R.id.btnAni);
        btn2 = (ImageButton) findViewById(R.id.btnLoca);
        btn3 = (ImageButton) findViewById(R.id.btnFoto);
        btn4 = (ImageButton) findViewById(R.id.btnAudi);
        btn5 = (ImageButton) findViewById(R.id.btnVideo);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Animacion.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Geolocalizacion.class);
                startActivity(i);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext(), Camara.class);
                startActivity(i);

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Sonido.class);
                startActivity(i);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Video.class);
                startActivity(i);
            }
        });

    }


}
