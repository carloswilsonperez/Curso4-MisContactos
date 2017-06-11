package com.example.administrador.curso4miscontactos.restApi.model;

import com.example.administrador.curso4miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by administrador on 11/06/17.
 */
// Clase para manejar la respuesta de la API de Instagram
public class ContactoResponse {

    ArrayList<Contacto> contactos;

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }
}
