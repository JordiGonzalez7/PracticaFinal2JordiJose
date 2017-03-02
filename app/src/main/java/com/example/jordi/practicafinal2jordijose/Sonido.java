package com.example.jordi.practicafinal2jordijose;


import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.io.IOException;

public class Sonido extends AppCompatActivity {

    private Button btn2, btn3;
    private boolean grabando = false, reproduciendo = false;
    private String nombreFi = null, nombreFi2 = null;
    private MediaRecorder mr = null;
    private MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonido);


        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        nombreFi = Environment.DIRECTORY_MUSIC + "/audio1.3gp";
        nombreFi2 = getExternalFilesDir(null) + "/audio2.3gp";


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
        mr = new MediaRecorder();
        mr.reset();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mr.setOutputFile(nombreFi);

        try {
            mr.prepare();

        } catch (IOException e) {
            e.printStackTrace();

        }
        mr.start();
    }

    private void nograbar(View v) {
        mr.stop();
        mr.release();
        mr = null;

    }

    private void repro(View v) {
        mp = new MediaPlayer();
        try {
            mp.setDataSource(nombreFi);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void norepro(View v) {
        mp.release();
        mp = null;

    }
}
