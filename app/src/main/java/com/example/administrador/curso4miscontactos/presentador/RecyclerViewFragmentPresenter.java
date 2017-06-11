package com.example.administrador.curso4miscontactos.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.administrador.curso4miscontactos.db.ConstructorContactos;
import com.example.administrador.curso4miscontactos.pojo.Contacto;
import com.example.administrador.curso4miscontactos.restApi.EndpointsApi;
import com.example.administrador.curso4miscontactos.restApi.adapter.RestApiAdapter;
import com.example.administrador.curso4miscontactos.restApi.model.ContactoResponse;
import com.example.administrador.curso4miscontactos.vista_fragment.IRecyclerViewFragmentView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        obtenerMediosRecientes();
        //obtenerContactosBaseDatos();
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
        //iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.generarGridLayout();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter(); // Intancia restApiAdapter

        //Creo un objeto EndpointsApi utilizando la instancia del RestApiAdapter y el metodo establecerConexionRestApiInstagram()
        // el cual devuelve un objeto de tipo EndpointsApi ya con la url-base cargada y esperando una petición a ejecutar
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram();

        // El metodo getRecentMedia realiza la petición y lo guarda en el objeto Call de la clase Retrofit
        Call<ContactoResponse> contactoResponseCall = endpointsApi.getRecentMedia();

        //Metodo para controlar el resultado de la respuesta, si trae datos o no
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override // Si la conexión es exitosa:
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body(); //obtiene solo la data del objeto json recibido
                contactos = contactoResponse.getContactos();// gurada el ArrayList de contactos
                mostrarContactosRV();
            }

            @Override // Si la conexión falla:
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(context, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();//Mensaje para el usuario
                // log para el programador
                Log.e("Fallo la conexión", t.toString());
            }
        });
    }
}
