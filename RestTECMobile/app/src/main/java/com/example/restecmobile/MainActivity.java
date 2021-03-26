package com.example.restecmobile;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        tvCantProductos = findViewById(R.id.tvCantProductos);
        btnVerCarro = findViewById(R.id.btnVerCarro);
        rvListaProductos = findViewById(R.id.rvListaProductos);
        rvListaProductos.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
        //parseJSON();
        listaProductos.add(new Producto("Pasta","Esto y aquello",100,20,10000));
        listaProductos.add(new Producto("Ensalada","Esto y aquello",10,10,20000));
        listaProductos.add(new Producto("Sopa","Esto y aquello",200,200,5000));
        listaProductos.add(new Producto("Hamburguesa","Esto y aquello",1000,25,20000));
        adaptador = new AdaptadorProductos(MainActivity.this, tvCantProductos,btnVerCarro,listaProductos,carroCompras);
        rvListaProductos.setAdapter(adaptador);
    }
    /**
     * Manejo de Solicitud de productos disponibles en el REST
     */
    private void parseJSON(int orderID){
        String postUrl = "http://localhost:5001";
        Date fecha= Calendar.getInstance().getTime();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, postUrl , null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject obj = response;
                    JSONArray jsonArray = obj.getJSONArray("recipes");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject productos = jsonArray.getJSONObject(i);
                        Producto producto = new Producto(productos.getString("recipeName"),productos.getString("ingredients"),productos.getInt("calories"),productos.getInt("calories"),productos.getDouble("Monto"));
                        listaProductos.add(producto);
                    }
                    adaptador = new AdaptadorProductos(MainActivity.this, tvCantProductos,btnVerCarro,listaProductos,carroCompras);
                    rvListaProductos.setAdapter(adaptador);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}