package com.fva.mislugares;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by DTIC-Dir on 18/10/2017.
 */

public class VistaLugarActivity extends AppCompatActivity {
    private long id;
    private Lugar lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        lugar = MainActivity.lugares.elemento((int) id);
        TextView nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView tipo = (TextView) findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());
        TextView direccion = (TextView) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        if (lugar.getTelefono() == 0) {
            findViewById(R.id.telefono).setVisibility(View.GONE);
        } else {
            findViewById(R.id.telefono).setVisibility(View.VISIBLE);
            TextView telefono = (TextView) findViewById(R.id.telefono);
            telefono.setText(Integer.toString(lugar.getTelefono()));
        }
        //TextView telefono = (TextView) findViewById(R.id.telefono);
        //telefono.setText(Integer.toString(lugar.getTelefono()));
        if (lugar.getUrl().isEmpty()) {
            findViewById(R.id.url).setVisibility(View.GONE);
        } else {
            TextView url = (TextView) findViewById(R.id.url);
            url.setText(lugar.getUrl());
        }
        TextView comentario = (TextView) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        TextView fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = (TextView) findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = (RatingBar) findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vista_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_compartir:
                return true;
            case R.id.accion_llegar:
                return true;
            case R.id.accion_editar:
                Intent i = new Intent(VistaLugarActivity.this, EdicionLugarActivity.class);
                i.putExtra("id", id);
                startActivityForResult(i, 1234);

                //startActivity(i);

                return true;
            case R.id.accion_borrar:

                borrarLugar(null, (int) id);
                //MainActivity.lugares.borrar((int) id);
                //finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void borrarLugar(View view, final Integer id) {

        new AlertDialog.Builder(this)
                .setTitle("Confirmar Eliminacion")
                .setMessage("Esta seguro que desea eliminar este lugar??")
                //.setView(entrada)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        MainActivity.lugares.borrar((int) id);
                        finish();
                        return;
                        //long id = Long.parseLong(entrada.getText().toString());
                        //Intent i = new Intent(MainActivity.this, VistaLugarActivity.class);
                        //i.putExtra("id", id);
                        //startActivity(i);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String res = bundle.getString("param1");
            String res2 = bundle.getString("nombre");
            // String res = data.getExtras().getString("direccion");
            //String res2 = data.getExtras().getString("nombre");
            Log.i("vistalugarActivity", res);
            Log.i("vistaluagraActivity", res2);
            lugar.setDireccion(res);
            lugar.setNombre(res2);
            TextView direccionT = (TextView) findViewById(R.id.direccion);
            direccionT.setText(res);
            TextView nombreT = (TextView) findViewById(R.id.nombre);
            nombreT.setText(res2);

        }
    }

}