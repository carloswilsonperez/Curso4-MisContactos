package com.example.administrador.curso4miscontactos.vista_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrador.curso4miscontactos.R;
import com.example.administrador.curso4miscontactos.adapter.ContactoAdaptador;
import com.example.administrador.curso4miscontactos.pojo.Contacto;
import com.example.administrador.curso4miscontactos.presentador.IRecyclerViewFragmentPresenter;
import com.example.administrador.curso4miscontactos.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by administrador on 14/05/17.
 */

public class RecyclerViewFragmentView extends Fragment implements IRecyclerViewFragmentView {

    private RecyclerView rvContactos;
    public ContactoAdaptador adaptador;
    private IRecyclerViewFragmentPresenter presenter;

    // Primero hay que sobreescribir el método onCreateView
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //    Con esta línea le asignamos la clase java al layout
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        rvContactos = (RecyclerView) v.findViewById(R.id.rvContactos);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }


    @Override
    public void generarLinearLayoutVertical() {
        // Instacia el linearLayoutManager que sirve para manejar la forma en que se ve la lista
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);// Determina la dirección del linearLayout
        rvContactos.setLayoutManager(llm);// Setea el RecyclerView con el objeto LinearLayoutManeger creado
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvContactos.setLayoutManager(gridLayoutManager);

    }

    @Override
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos) {
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(ContactoAdaptador adaptador) {
        rvContactos.setAdapter(adaptador);
    }
}
