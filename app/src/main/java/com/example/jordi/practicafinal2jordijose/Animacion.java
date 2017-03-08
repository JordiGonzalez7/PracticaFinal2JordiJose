package com.example.jordi.practicafinal2jordijose;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Animacion extends AppCompatActivity {


    private Button btn3A,btn1,btn2,btn3,btn4;
    private ImageView img1,img2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacion);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn3A = (Button)findViewById(R.id.btn33);

        img1 = (ImageView)findViewById(R.id.img1);

        img2f = (ImageView)findViewById(R.id.img2);
        AnimationDrawable animacio = (AnimationDrawable) img2f.getDrawable();
        animacio.start();





        //interpolacion
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animacion = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animacion1);
                img1.startAnimation(animacion);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animacion = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.aparecer);
                img1.startAnimation(animacion);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animacion = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rodar);
                img1.startAnimation(animacion);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animacion = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.botar);
                img1.startAnimation(animacion);
            }
        });



        //boton atras
        btn3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
