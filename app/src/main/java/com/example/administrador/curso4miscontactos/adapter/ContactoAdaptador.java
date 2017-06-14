package com.example.administrador.curso4miscontactos.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrador.curso4miscontactos.pojo.Contacto;
import com.example.administrador.curso4miscontactos.DetalleContacto;
import com.example.administrador.curso4miscontactos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by administrador on 25/04/17.
 */

/*
    Esta clase hereda de la clase RecyclerView.Adater y recibe una colección de datos.
    La Clase ContactoAdaptador va a estár manejando la lista de contactos y es la encargada de pasar los datos a la
    clase interna ContactoViewHolder
 */
public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> {

    private static final String TAG = "Depurador";
    ArrayList<Contacto> contactos;
    Activity activity;

    //******** Constructor ********************************
    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity){
        this.contactos = contactos;
        this.activity = activity;
    }

    /* Método que va a inflar el layout y lo pasara al ViewHolder para que obtenga los views */
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //En esta linea se inidica que layout va representar los datos del RecyclerView
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    /* Método que asocia cada elemento de la lista con cada view.
       O dicho de otro modo, es donde se setean los datos de la clase ContactoViewHolder con los datos de la lista recibida */
    @Override
    public void onBindViewHolder(final ContactoViewHolder contactoViewHolder, int position) {
        final Contacto contacto = contactos.get(position); //Obtiene todos los datos de un contacto
    //    contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        String ruta = contacto.getUrlFoto();
        ruta = ruta.replaceAll("\"", ""); //Quito las comillas dobles que vienen con la url desde el json
 /*       Log.i(TAG, "La ruta la url es:"+ ruta);  */
        Picasso.with(activity) // Libreria para traer las fotos
                .load(ruta) // trae la foto del usuario
                .placeholder(R.mipmap.muchacha1) // ruta a una foto por defecto para el caso que no se encuentre la foto
                .into(contactoViewHolder.imgFoto); // ImagenView dode se va a mostrar la foto
        contactoViewHolder.tvLikes.setText(String.valueOf(contacto.getLikes()));

        // Hace que las imagenes de la lista sean clickeables
        contactoViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetalleContacto.class);
                intent.putExtra("url", contacto.getUrlFoto());
                intent.putExtra("like", contacto.getLikes());
                activity.startActivity(intent);
            }
        });
/*
        // Boton likes
        contactoViewHolder.btnLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste like a " + contacto.getNombre(), Toast.LENGTH_SHORT).show();

                ConstructorContactos constructorContactos = new ConstructorContactos(activity);
                constructorContactos.darLikeContacto(contacto); // Agrego un nuevo registro de like al contacto
                contactoViewHolder.tvLikes.setText(constructorContactos.obtenerLikesContacto(contacto) + " Likes"); ; // Obtengo el número total de likes que tiene el contacto y lo seteo en tvLikes para mostrarlo
            }
        });
       */
    }

    //Método que devuelve la cantidad de elementos que contiene mi lista de contactos
    @Override
    public int getItemCount() {
        return contactos.size();
    }


    // **************************  Clase interna ***********************************************************
    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        // Aquí hay que declarar todos los view que tengo definidos en el cardView contactos
        private ImageView imgFoto;
        private ImageView imgBone;
        private TextView tvLikes;

        // En el contructor hay que asociar cada objeto declarado con su respectivo view
        public ContactoViewHolder(View itemView) {
            super(itemView);
            // Nota hay que hacer uso del objeto "itemView"recibido para hacer el casting y llegar al view
            imgFoto         = (ImageView) itemView.findViewById(R.id.imgFoto);
            //tvNombreCv      = (TextView) itemView.findViewById(R.id.tvNombreCv);
            //tvTelefonoCv    = (TextView) itemView.findViewById(R.id.tvTelefonoCv);
            tvLikes         = (TextView) itemView.findViewById(R.id.tvLikesDetalle);
            //btnLikes        = (ImageButton) itemView.findViewById(R.id.btnLikes);
        }
    }
}
