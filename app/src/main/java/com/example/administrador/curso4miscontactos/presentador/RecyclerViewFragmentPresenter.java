package com.example.administrador.curso4miscontactos.presentador;

import android.content.Context;

import com.example.administrador.curso4miscontactos.db.ConstructorContactos;
import com.example.administrador.curso4miscontactos.pojo.Contacto;
import com.example.administrador.curso4miscontactos.vista_fragment.IRecyclerViewFragmentView;

import java.util.ArrayList;

/**
 * Created by administrador on 23/05/17.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    //Delcaro los objeto globales
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Contacto> contactos;

    // El constructor recibe como parametro una instancia de la interface modelo y el contexto
    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerContactosBaseDatos();
    }


    @Override
    public void obtenerContactosBaseDatos() {
        constructorContactos = new ConstructorContactos(context);
        contactos = constructorContactos.obtenerDatos(); //***** Aquí es donde se juntan los datos con la presentación ******
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        // Hay que inicializar el adaptador, para ello primero se debe crear el adaptador y pasarele el ArrayList contactos
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos));
        // Luego se debe indicar que genere el LinearLayoutVertical
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
