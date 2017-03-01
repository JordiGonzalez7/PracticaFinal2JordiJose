package com.example.jordi.practicafinal2jordijose;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class Camara extends AppCompatActivity {

    private Button btn1c, btn2c, btn3c;
    private ImageView img1;
    private int tipo;
    private Bitmap bMap;


    private Uri idIMG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        btn1c = (Button) findViewById(R.id.btn1);
        btn2c = (Button) findViewById(R.id.btn2);
        btn3c = (Button) findViewById(R.id.btn3);
        img1 = (ImageView) findViewById(R.id.img1);


        btn1c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);

                File fotodir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "FotosPrueba");
                fotodir.mkdirs();

                File foto = new File(fotodir, "foto1.jpg");
                Uri uriSavedImage = Uri.fromFile(foto);

                i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

                idIMG = Uri.fromFile(foto);
                tipo = 1;
                startActivityForResult(i, tipo);
            }
        });


        btn2c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);

                File fotodir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "FotosPrueba");
                fotodir.mkdirs();

                File foto = new File(fotodir, "foto1.jpg");
                Uri uriSavedImage = Uri.fromFile(foto);

                i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

                idIMG = Uri.fromFile(foto);
                tipo = 2;
                startActivityForResult(i, tipo);

            }
        });


        btn3c.setOnClickListener(new View.OnClickListener() {
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
        Log.e("requestCode", "requestCode:" + requestCode);
        Log.e("RESULT_OK", "RESULT_OK:" + RESULT_OK);


        if (requestCode == 1 && resultCode == RESULT_OK) {

            switch (tipo) {

                case 1:
                    bMap = BitmapFactory.decodeFile(
                            getExternalFilesDir(Environment.DIRECTORY_PICTURES) +
                                    "/FotosPrueba/" + "foto1.jpg");

                    img1.setImageBitmap(bMap);
                    break;
                case 2:
                    bMap = BitmapFactory.decodeFile(
                            getExternalFilesDir(Environment.DIRECTORY_PICTURES) +
                                    "/FotosPrueba/" + "foto1.jpg");

                    img1.setImageBitmap(bMap);
                    img1.getLayoutParams().height = 77;
                    img1.getLayoutParams().width = 55;
                    break;

            }
        }

    }
}
