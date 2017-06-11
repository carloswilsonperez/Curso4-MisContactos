package com.example.administrador.curso4miscontactos.restApi;

import com.example.administrador.curso4miscontactos.restApi.model.ContactoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by administrador on 10/06/17.
 */

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)//Peticion GET a la api de instagram que va a ser usada por el metodo seguido
    Call<ContactoResponse> getRecentMedia(); //Retrofit necesita usar la clase Call
}
