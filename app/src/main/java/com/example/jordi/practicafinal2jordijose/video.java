package com.example.jordi.practicafinal2jordijose;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    private Button btn1V,btn2V,btn3V;


    public static final int INTENT_GRABAR_VIDEO= 1;
    private Uri uriVideo = null;
    private VideoView visor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        btn1V = (Button) findViewById(R.id.btn1);
        btn2V = (Button) findViewById(R.id.btn2);
        btn3V = (Button) findViewById(R.id.btn3);
        visor = (VideoView)findViewById(R.id.vv1);

        btn2V.setEnabled(false);


        btn1V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //con este intent se activa la camara para grabar un video

                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, INTENT_GRABAR_VIDEO);

            }
        });




        btn2V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cuando el video esta cargado satisfactoriamente en el uri se le asigna al Video View para que este se reproduzca
                Toast.makeText(getApplicationContext(),"Reproduciendo...",Toast.LENGTH_LONG).show();
                visor.setVideoURI(uriVideo);
                visor.start();

            }
        });



        btn3V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //una vez se ha grabado todo lo que el usuario quiera, el uri se encarga de alamacenar el video
        //para previamente reproducirlo

        if (resultCode == RESULT_OK){

            if(requestCode == INTENT_GRABAR_VIDEO){
                uriVideo = data.getData();
                Toast.makeText(getApplicationContext(),uriVideo.getPath(),Toast.LENGTH_LONG).show();

                btn2V.setEnabled(true);

            }
        } else if (resultCode == RESULT_CANCELED){
            uriVideo = null;
            Toast.makeText(getApplicationContext(),"Operacion cancelada",Toast.LENGTH_LONG).show();
        }
    }
}
