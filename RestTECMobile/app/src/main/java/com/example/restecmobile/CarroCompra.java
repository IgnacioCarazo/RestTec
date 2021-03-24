package com.example.restecmobile;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.restecmobile.URLs.URL_Producto;
/**
 * @class CarroCompra
 * Configura la vista 'activity_carro_compras'
 * Llena la Lista carroCompra, busqueda al ReciclerView por id.rvListaCarro && setLayoutManager
 * Llena el texto busqueda por id
 * Crea adaptador, une la lista y el texto
 * Insertar adaptador en ReciclerView
 * @author JosephJ
 */
public class CarroCompra extends AppCompatActivity {
    List<Producto> carroCompra;
    AdaptadorCarroCompra adaptador;
    RecyclerView rvListaCarro;
    TextView tvTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_compra);
        carroCompra = (List<Producto>) getIntent().getSerializableExtra("CarroCompras");
        rvListaCarro = findViewById(R.id.rvListaCarro);
        rvListaCarro.setLayoutManager(new GridLayoutManager(CarroCompra.this, 1));
        tvTotal = findViewById(R.id.tvTotal);
        adaptador = new AdaptadorCarroCompra(CarroCompra.this, carroCompra, tvTotal);
        rvListaCarro.setAdapter(adaptador);
        Button confirmaPedido = (Button)findViewById(R.id.botonConfirma);
        confirmaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0 ;i  < carroCompra.size(); i++){
                    Producto pedido = carroCompra.get(i);
                    String nombrePedido = pedido.getNOM_PRODUCTO();
                    String detallePedido = pedido.getDESCRIPCION();
                    Intent intent = new Intent(CarroCompra.this, EsperaPedido.class);
                    intent.putExtra("Espera de pedido", (Serializable) carroCompra);
                    CarroCompra.this.startActivity(intent);
                    CarroCompra.this.finish();
                    //jsonParse(nombrePedido,detallePedido);
                }
            }
        });
    }
    /**
     * Manejo de POST de los platillos seleccionados
     */
    private void jsonParse(String nombrePedido, String detallePedido){
        String JSON_URL = URL_Producto;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        /*
                        try {
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                        Intent intent = new Intent(CarroCompra.this, EsperaPedido.class);
                        intent.putExtra("Espera de pedido", (Serializable) carroCompra);
                        CarroCompra.this.startActivity(intent);
                        CarroCompra.this.finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Nombre",nombrePedido);
                params.put("Descripcion",detallePedido);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

