package com.example.restecmobile;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import static com.example.restecmobile.URLs.URL_Producto;
/**
 * @class MainActivity
 * En esta clase se habilitan los productos posibles a seleccionar asi como el boton que cofirma en el envio de estos
 * @author JosephJ
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvCantProductos;
    private Button btnVerCarro;
    private RecyclerView rvListaProductos;
    private AdaptadorProductos adaptador;
    private List<Producto> listaProductos = new ArrayList<>();
    private List<Producto> carroCompras = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final TextView mensaje = (TextView)findViewById(R.id.Mensaje);
        //Intent i = this.getIntent();
        //String nombre = i.getStringExtra("Nombre");
        //mensaje.setText(mensaje.getText()+" "+nombre);
        tvCantProductos = findViewById(R.id.tvCantProductos);
        btnVerCarro = findViewById(R.id.btnVerCarro);
        rvListaProductos = findViewById(R.id.rvListaProductos);
        rvListaProductos.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
        //parseJSON();
        listaProductos.add(new Producto("1","Producto1","Este producto es prometedor",10000));
        listaProductos.add(new Producto("2","Producto2","Este producto es masomenos prometedor",7000));
        listaProductos.add(new Producto("3","Producto3","Este producto es cool",3000));
        listaProductos.add(new Producto("4","Producto4","Este producto es una picha",20));
        adaptador = new AdaptadorProductos(MainActivity.this, tvCantProductos,btnVerCarro,listaProductos,carroCompras);
        rvListaProductos.setAdapter(adaptador);
    }
    /**
     * Manejo de Solicitud de productos disponibles en el REST
     */
    private void parseJSON(){
        String JSON_URL = URL_Producto;//https://201.202.14.87:3000/Producto/
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("Productos");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject productos = jsonArray.getJSONObject(i);
                                Producto producto = new Producto(productos.getString("ID"),productos.getString("Nombre"),productos.getString("Descripcion"),productos.getDouble("Monto"));
                                listaProductos.add(producto);
                            }
                            adaptador = new AdaptadorProductos(MainActivity.this, tvCantProductos,btnVerCarro,listaProductos,carroCompras);
                            rvListaProductos.setAdapter(adaptador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
  /*
    private void parseJSON(){
        String JSON_URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("heroes");
                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Producto hero = new Producto("ID1",heroObject.getString("name"), heroObject.getString("imageurl"),2000);
                                //adding the hero to herolist
                                listaProductos.add(hero);
                            }
                            adaptador = new AdaptadorProductos(MainActivity.this, tvCantProductos,btnVerCarro,listaProductos,carroCompras);
                            rvListaProductos.setAdapter(adaptador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
     */