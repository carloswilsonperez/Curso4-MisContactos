package com.example.administrador.curso4miscontactos.vista_fragment;

import com.example.administrador.curso4miscontactos.adapter.ContactoAdaptador;
import com.example.administrador.curso4miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by administrador on 23/05/17.
 */

public interface IRecyclerViewFragmentView {


    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos);

    public void inicializarAdaptadorRV(ContactoAdaptador adaptador);

}
