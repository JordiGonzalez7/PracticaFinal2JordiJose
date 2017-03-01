package com.example.jordi.practicafinal2jordijose;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Camara extends AppCompatActivity {

    private Button btn1, btn2, btn3;
    private ImageView img1;
    public final static int APP_CAMERA = 1;

    private Uri idIMG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        img1 = (ImageView) findViewById(R.id.img1);


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }


    public void foto(View v) {
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");

        File foto = new File(getExternalFilesDir(null), "fotos.jpg");
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));

        idIMG = Uri.fromFile(foto);
        startActivityForResult(i, APP_CAMERA);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("requestCode", "requestCode:" + requestCode);
        Log.e("RESULT_OK", "RESULT_OK:" + RESULT_OK);

        switch (requestCode) {

            case APP_CAMERA:
                if (resultCode == RESULT_OK) {


                    ContentResolver cr = getContentResolver();
                    cr.notifyChange(idIMG, null);
                    Bitmap bitmap;

                    try {
                        bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, idIMG);
                        int alt = (int) (bitmap.getHeight() * 1080 / bitmap.getWidth());
                        Bitmap reduit = Bitmap.createScaledBitmap(bitmap, 1080, alt, true);
                        img1.setImageBitmap(reduit);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "No se ha cargado la imagen " + idIMG.toString(), Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }

    }
}
