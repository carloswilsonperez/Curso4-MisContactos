package com.example.administrador.curso4miscontactos.restApi;

/**
 * Created by administrador on 10/06/17.
 */

public final class ConstantesRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5557323253.5477f1a.a6c8d1cf0f9747fe91b9c884bc63fcc4";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    // Petición para traer todas las imágenes del usuario https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    public static final String URL_GET_RECENT_MEDIA_USER = ROOT_URL + KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}
