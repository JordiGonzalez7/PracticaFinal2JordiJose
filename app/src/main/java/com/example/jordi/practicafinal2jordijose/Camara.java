package com.example.jordi.practicafinal2jordijose;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Camara extends AppCompatActivity {

    //creamos lo componentes

    private Button btn1c, btn2c, btn3c;
    private ImageView img1c, img2c;
    public int tipo;
    private Bitmap bMap;
    private File fotodir;

    private final static int normal = 1;
    private final static int reducida = 2;


    private Uri idIMG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        //asignamos los componentes

        btn1c = (Button) findViewById(R.id.btn1);
        btn2c = (Button) findViewById(R.id.btn2);
        btn3c = (Button) findViewById(R.id.botonAtras);
        img1c = (ImageView) findViewById(R.id.img1);
        img2c = (ImageView) findViewById(R.id.img2);

        //creamos el directorio donde guardar los fotos

        fotodir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "FotosPrueba");
        fotodir.mkdirs();


        btn1c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // en este boron creamos el intent que abrira la camara y guardara la foto realizada en la carpeta correcta
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);


                File foto = new File(fotodir, "foto1.jpg");
                Uri uriSavedImage = Uri.fromFile(foto);

                i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

                idIMG = Uri.fromFile(foto);

                startActivityForResult(i, normal);
            }
        });


        btn2c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //en este boton se hace lo mismo que en el anterio pero con un result code diferente para mostrar la foto
                //mediante startactivityforresult de otra manera al primer boton

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);


                File foto = new File(fotodir, "foto2.jpg");
                Uri uriSavedImage = Uri.fromFile(foto);

                i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

                idIMG = Uri.fromFile(foto);

                startActivityForResult(i, reducida);

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
//en funcion del result code que hayamos recibido tendremos un tamaño u otro de la foto

        if ((requestCode == normal || requestCode == reducida) && resultCode == RESULT_OK) {

            switch (requestCode) {

                case normal:
                    try {

                        bMap = BitmapFactory.decodeFile(
                                getExternalFilesDir(Environment.DIRECTORY_PICTURES) +
                                        "/FotosPrueba/" + "foto1.jpg");


                        img1c.setImageBitmap(bMap);
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), "Ha habido un erro al cargar la imagen", Toast.LENGTH_LONG).show();

                    }


                    break;
                case reducida:
                    try {
                //con el bitmap establecemos una resolucion diferente a la original
                        bMap = BitmapFactory.decodeFile(
                                getExternalFilesDir(Environment.DIRECTORY_PICTURES) +
                                        "/FotosPrueba/" + "foto2.jpg");
                        int alt = (int) (bMap.getHeight() * 1080 / bMap.getWidth());
                        Bitmap reduit = Bitmap.createScaledBitmap(bMap, 1080, alt, true);
                        img2c.setImageBitmap(reduit);

                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), "Ha habido un erro al cargar la imagen", Toast.LENGTH_LONG).show();

                    }

                    break;

            }
        } else {

            Toast.makeText(getApplicationContext(), "Operacion cancelada", Toast.LENGTH_LONG).show();

        }

    }
}
