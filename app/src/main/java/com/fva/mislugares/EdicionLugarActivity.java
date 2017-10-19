package com.fva.mislugares;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by DTIC-Dir on 18/10/2017.
 */

public class EdicionLugarActivity extends AppCompatActivity {
    private long id;
    private Lugar lugar;
    private EditText nombre;
    private Spinner tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;
    private Button aceptar;
    private Button cancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_lugar);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        lugar = MainActivity.lugares.elemento((int) id);
        nombre = (EditText) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        direccion = (EditText) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        findViewById(R.id.telefono).setVisibility(View.VISIBLE);
        telefono = (EditText) findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));

        url = (EditText) findViewById(R.id.url);
        url.setText(lugar.getUrl());
        comentario = (EditText) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        aceptar = (Button) findViewById(R.id.button9);
        cancelar = (Button) findViewById(R.id.button8);

        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                EditText nombre2 = (EditText) findViewById(R.id.nombre);

                Log.i("EnterpriseActivity.java",String.valueOf(nombre2.getText()));
                Log.i("EnterpriseActivity.java",String.valueOf(direccion.getText()));

                lanzarRespuesta(null, String.valueOf(direccion.getText()),String.valueOf(nombre2.getText()));

            }
        });
    }


    protected void lanzarRespuesta (View view, String adireccion, String anombre){
        Intent intent = new Intent();

        Log.i("Edicionlugar",adireccion);
        Log.i("Edicionlugar",anombre);
        intent.putExtra("param1", adireccion);
        intent.putExtra("nombre", anombre);
//intent.putExtra("param1","mi casa");
//intent.putExtra("nombre","mi nombre");
        //intent.putExtra("lugar", adireccion);
        //intent.putExtra("nombre", anombre);
        setResult(RESULT_OK, intent);
        finish();



    }






    }





