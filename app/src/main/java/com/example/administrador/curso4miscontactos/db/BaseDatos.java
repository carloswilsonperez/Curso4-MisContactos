package com.example.administrador.curso4miscontactos.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log; //************************************* Solo para depuración
import com.example.administrador.curso4miscontactos.pojo.Contacto;
import java.util.ArrayList;


/**
 * Created by administrador on 24/05/17.
 */

public class BaseDatos extends SQLiteOpenHelper{

    Context context;
    private static final String TAG = "Depurador";


    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override // Aquí se crea toda la estructura de la Base de Datos
    public void onCreate(SQLiteDatabase db) {
        // query para crear la tabla contacto
        String queryCrearTablaContacto =
                "CREATE TABLE "+ConstantesBaseDatos.TABLE_CONTACTS+"("+
                        ConstantesBaseDatos.TABLE_CONTACTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE + " TEXT, "+
                        ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO + " TEXT, "+
                        ConstantesBaseDatos.TABLE_CONTACTS_EMAIL + " TEXT, "+
                        ConstantesBaseDatos.TABLE_CONTACTS_FOTO + " INTEGER"+
                        ")";

        String queryCrearTablaLikesContacto =
                "CREATE TABLE "+ConstantesBaseDatos.TABLE_LIKES_CONTACTO+"("+
                        ConstantesBaseDatos.TABLE_LIKES_CONTACTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        ConstantesBaseDatos.TABLE_LIKES_CONTACTO_ID_CONTACTO + " INTEGER, "+
                        ConstantesBaseDatos.TABLE_LIKES_CONTACTO_NUMERO_LIKES + " INTEGER, "+
                        "FOREIGN KEY ("+ ConstantesBaseDatos.TABLE_LIKES_CONTACTO_ID+ ") "+
                        "REFERENCES "+ ConstantesBaseDatos.TABLE_CONTACTS+ "("+ ConstantesBaseDatos.TABLE_CONTACTS_ID + ")"+
                        ")";

        // execSQL es el metodo para ejecutar una consulta sql directamente
        db.execSQL(queryCrearTablaContacto);
        db.execSQL(queryCrearTablaLikesContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_CONTACTO);
        onCreate(db);
    }

    // método para obtener todos los contactos, que devuelve un ArrayList de contactos
    public ArrayList<Contacto> obtenerTodosLosContactos(){

        ArrayList<Contacto> contactos = new ArrayList<Contacto>(); // Instacia el ArrayList para devolver
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_CONTACTS; // Consulta sql
        SQLiteDatabase db = this.getWritableDatabase(); // Abre la base de datos en modo escritura
        Cursor registros = db.rawQuery(query, null); //Obtiene todas los filas y las guarda en objeto de tipo curso registros

        while (registros.moveToNext()){

            Contacto contactoActual = new Contacto();
            contactoActual.setId(registros.getInt(0));
            contactoActual.setNombre(registros.getString(1));
            contactoActual.setTelefono(registros.getString(2));
            contactoActual.setEmail(registros.getString(3));
            contactoActual.setFoto(registros.getInt(4));
            // Query para obtener los likes
            String queryLikes = "SELECT COUNT("+ ConstantesBaseDatos.TABLE_LIKES_CONTACTO_NUMERO_LIKES +")"+
                                " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACTO +
                                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACTO_ID_CONTACTO +"="+contactoActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null); // ejecuta la consulta y obtengo el número de likes

            if (registrosLikes.moveToNext()){
                contactoActual.setLikes(registrosLikes.getInt(0)); // Setea el número de likes al contacto actual
            }else{
                contactoActual.setLikes(0); // si no hay likes en el contacto setea el número de likes a cero
            }

            contactos.add(contactoActual);
        }
        db.close(); // Cierra la conexión a la BD
        return contactos;
    }

    //     Método para ingresar un registro. Este recibe como argumento un contenedor de valores
    public void insertarContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase(); // Abre la base de datos en modo escritura
        db.insert(ConstantesBaseDatos.TABLE_CONTACTS, null, contentValues); // metodo recomendato para evitar la inyección sql
        db.close();
    }


    // Método para insertar los likes
    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_CONTACTO, null, contentValues);
        db.close();
    }


    public int obtenerLikesContacto(Contacto contacto){
        int likes = 0;
        String query = "SELECT COUNT("+ ConstantesBaseDatos.TABLE_LIKES_CONTACTO_NUMERO_LIKES +")"+
                        " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACTO +
                        " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACTO_ID_CONTACTO +"="+contacto.getId();

        SQLiteDatabase db = this.getWritableDatabase(); //abro la conexión
        Cursor registros = db.rawQuery(query, null); // ejecuta la consulta y obtengo los regitros

        if (registros.moveToNext()){
            likes = registros.getInt(0); //Como tengo un valor solo obtengo la columna con index 0
            Log.d(TAG, "El valor de likes es : "+ likes);
        }

        db.close(); // Cierra la conexión
        return likes;
    }
}
