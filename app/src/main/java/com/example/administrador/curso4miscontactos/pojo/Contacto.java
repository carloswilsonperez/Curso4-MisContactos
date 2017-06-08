package com.example.administrador.curso4miscontactos.pojo;

/**
 * Created by administrador on 17/04/17.
 */

public class Contacto {

    private int id; //El id no es necesario que este en el contructor, solo necesita los geter y seter
    private String nombre;
    private String telefono;
    private String email;
    private int foto;
    private int likes;


    public Contacto(int foto, String nombre, String telefono, String email, int likes) {
        this.foto = foto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.likes = likes;
    }

    public Contacto() {
        this.foto = 0;
        this.nombre = "";
        this.telefono = "";
        this.email = "";
        this.likes = 0;
    }


    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
