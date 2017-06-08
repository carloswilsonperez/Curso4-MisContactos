package com.example.administrador.curso4miscontactos.db;

import android.content.ContentValues;
import android.content.Context;
import com.example.administrador.curso4miscontactos.R;
import com.example.administrador.curso4miscontactos.pojo.Contacto;
import java.util.ArrayList;

/**
 * Created by administrador on 23/05/17.
 */
/*
    Esta Clase es la que interactua con los método de la base de datos
 */
public class ConstructorContactos {

    private static final Integer LIKE = 1;
    Context context;

    public ConstructorContactos(Context context) {
        this.context = context;
    }

    //Como standard siempre que se obtienen datos se deben devolver en un arraylist
    public ArrayList<Contacto> obtenerDatos(){
       ArrayList<Contacto> contactos = new ArrayList<Contacto>();  //instancio el ArrayList contactos
        // cargo algunos contactos
      /*  contactos.add(new Contacto(R.mipmap.muchacha2 ,"Viviana García", "", "vivigar15@hotmail.com", 5));
        contactos.add(new Contacto(R.mipmap.muchacha3, "Valentina Arrieta", "243561444", "valentina@gmail.com", 2));
        contactos.add(new Contacto(R.mipmap.muchacho2, "Bruno Arrieta", "24684633", "pedro-sanches@hotmail.com", 4));
        contactos.add(new Contacto(R.mipmap.muchacho1, "Julio Arrieta", "43421389", "jaservice@vera.com.uy", 1));
        contactos.add(new Contacto(R.mipmap.muchacha1,"Sofia Arrieta", "099828473", "sofia@gmail.com", 8)); */

        BaseDatos db = new BaseDatos(context); // Intacio la clase BaseDatos
        contactos = db.obtenerTodosLosContactos(); // Primero obtengo todos los contactos, para verificar si la bd esta vacía
        if (contactos.size() == 0) {
            insertarContactos(db); // Si no hay contactos cargados los agrego
            return db.obtenerTodosLosContactos();
        }else {
            return contactos;
        }
    }


    public void insertarContactos(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Viviana García");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "092011758");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "vivigar15@hotmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.mipmap.muchacha2);
        db.insertarContacto(contentValues); // Inserta los datos del primer contacto

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Valentina Arrieta");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "243561444");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "valentina@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.mipmap.muchacha3);
        db.insertarContacto(contentValues); // Inserta los datos del segundo contacto

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Bruno Arrieta");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "24684633");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "bruno@hotmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.mipmap.muchacho2);
        db.insertarContacto(contentValues); // Inserta los datos del tercer contacto

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Julio Arrieta");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "091650971");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "jaservice@vera.com.uy");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.mipmap.muchacho1);
        db.insertarContacto(contentValues); // Inserta los datos del curato contacto

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "sofia@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "sofia@hotmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.mipmap.muchacha1);
        db.insertarContacto(contentValues); // Inserta los datos del quinto contacto

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Jose Arrieta");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "099645789");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "jose@hotmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.mipmap.muchacho3);
        db.insertarContacto(contentValues); // Inserta los datos del tercer contacto
    }


    // darLikeContacto lo que hace es agregar un registo en la tabla de likes y recibe al contacto como parametro
    public void darLikeContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACTO_ID_CONTACTO, contacto.getId());// se pasa el id de contacto al que se le ha hecho like
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACTO_NUMERO_LIKES, LIKE);
        db.insertarLikeContacto(contentValues);
    }


    public int obtenerLikesContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context); // Instacio el objeto BaseDatos
        return db.obtenerLikesContacto(contacto); // Ejecuta el metodo para obtener el numero de likes y devuelve un integer
    }
}
