package com.example.administrador.curso4miscontactos.restApi.adapter;

import com.example.administrador.curso4miscontactos.restApi.ConstantesRestApi;
import com.example.administrador.curso4miscontactos.restApi.EndpointsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by administrador on 11/06/17.
 */

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) // deserializacion automatica de los datos json
                .build();
        return retrofit.create(EndpointsApi.class);
    }

}
