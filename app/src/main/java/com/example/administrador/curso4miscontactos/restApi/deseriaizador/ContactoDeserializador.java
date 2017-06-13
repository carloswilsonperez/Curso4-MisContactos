package com.example.administrador.curso4miscontactos.restApi.deseriaizador;

import com.example.administrador.curso4miscontactos.pojo.Contacto;
import com.example.administrador.curso4miscontactos.restApi.model.ContactoResponse;
import com.example.administrador.curso4miscontactos.restApi.model.JsonKeys;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by administrador on 11/06/17.
 */
//Clase para deserializar los datos y llevarlos a la forma de la clase del modelo ContactoResponse
public class ContactoDeserializador implements JsonDeserializer<ContactoResponse> {
    @Override
    public ContactoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ContactoResponse contactoResponse = gson.fromJson(json, ContactoResponse.class);//
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONCE_ARRAY);//Obtengo el array data del json
        contactoResponse.setContactos(deserealizarContactoDeJson(contactoResponseData));//llama al metodo contiguo para deserializar
        return contactoResponse;
    }

    public ArrayList<Contacto> deserealizarContactoDeJson(JsonArray contactoResponseData){

        ArrayList<Contacto> contactos = new ArrayList<>();

        for (int i = 0; i < contactoResponseData.size(); i++) {
            JsonObject contactoResponseDataObject = (JsonObject) contactoResponseData.get(i).getAsJsonObject();//Obtiene un elemento objeto del array
            JsonObject userJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER); //obtiene el objeto usuario
            String id = userJson.get(JsonKeys.USER_ID).getAsString(); //obtiene el id del usuario
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString(); //obtiene el nombre

            JsonObject imageJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);// otiene el objeto imagen del json
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION); // obtiene el objeto resolucion que dentro de imgen
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).toString(); //Obtiene la url de la foto

            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES); //Obtiene el objeto likes
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt(); //Obtiene el numero de likes
            // Lleno los datos del usuario actual
            Contacto contactoActual = new Contacto();
            contactoActual.setId(id);
            contactoActual.setNombreCompleto(nombreCompleto);
            contactoActual.setUrlFoto(urlFoto);
            contactoActual.setLikes(likes);

            contactos.add(contactoActual); //Guardo al usuario actual en el array contactos
        }

        return contactos;
    }
}
