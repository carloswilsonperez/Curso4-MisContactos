package com.example.administrador.curso4miscontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    private static final String KEY_EXTRA_URL = "url";
    private static final String KEY_EXTRA_LIKE = "like";
    private ImageView imgFotoDetalle;
    private TextView tvLikesDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        // Se activa el soporte para el ActionBar
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        // Activando el uso de la navegación hacia atras en el ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Recibo los parametros que se seleccionaron
         el el MainActivity y los guardo en el Bundle parametros */
        Bundle parametros = getIntent().getExtras();
        String url = parametros.getString(KEY_EXTRA_URL);
        int likes = parametros.getInt(KEY_EXTRA_LIKE);

        //Obtengo los TextView de la activiad principal para poder manipularlos
        tvLikesDetalle = (TextView) findViewById(R.id.tvLikesDetalle);
        tvLikesDetalle.setText(String.valueOf(likes));

    }

/*

    //Este metodo lo crea automaticamente para verificar el permiso de llamadas en tiempo de ejecucion
    public void llamar(View v) {
        String telefono = tvTelefono.getText().toString();
        // Creamos un intent implicito porque ejecuta una aplicacion externa
        //método simple, con el metod ACTION_CALL para llamar
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    }

    public void enviarMail(View v){
        String email = tvEmail.getText().toString();
        Intent emailIntent = new Intent((Intent.ACTION_SEND));//define el intent y la accion
        emailIntent.setData(Uri.parse("mailto:"));   //Indica que se enviara un correo
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email); //email es el correo del que envia
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Este es un correo enviado desde android.");//cuerpo del correo en texto plano
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Tema del mail");// Indicar el Tema del mensaje
        emailIntent.putExtra(Intent.EXTRA_TITLE, "Titulo del mail");
        emailIntent.setType("message/rfc822");// indica que tipo de aplicación debe buscar para enviar el email.
        startActivity(Intent.createChooser(emailIntent, "Email"));//el metodo createChooser nos da a elejir una apliacion de mail
    }
/*
    /*
        Metodo que para iniciar el MainActivity al presionar la tecla back
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode == KeyEvent.KEYCODE_BACK){ //si se pulso la tecla back
            Intent intent = new Intent(DetalleContacto.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onKeyDown(keyCode, event);
    }
}
