package com.example.jordi.practicafinal2jordijose;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class Sonido extends AppCompatActivity {

    private Button btn2, btn3;
    private boolean grabando = false, reproduciendo = false;
    private String nombreFi = null, nombreFi2 = null;
    private MediaRecorder mr = null;
    private MediaPlayer mp = null;
    private File AudioDir, Audio;

    public static final int REQUEST_MICROPHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonido);


        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);



        AudioDir = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "PruebaAudio");
        AudioDir.mkdirs();


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (reproduciendo) {
                    norepro(v);
                } else {

                    repro(v);
                }
                reproduciendo = !reproduciendo;


            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }
        });

    }

    public void OnclickGrabar(View v) {


        if (grabando) {

            nograbar(v);
        } else {

            grabar(v);
        }
        grabando = !grabando;


    }


    private void grabar(View v) {


        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) getApplicationContext(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_MICROPHONE);

        }
        Audio = new File(AudioDir, "audio.3gp");

        mr = new MediaRecorder();
        mr.reset();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        mr.setOutputFile(Audio.getAbsolutePath());

        try {
            mr.prepare();
            mr.start();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    private void nograbar(View v) {

        try{
            mr.stop();
            mr.release();
            mr = null;
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void repro(View v) {
        mp = new MediaPlayer();
        mp.reset();
        try {
            mp.setDataSource(Audio.getAbsolutePath());
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void norepro(View v) {
        try {
            mp.release();
            mp = null;

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
