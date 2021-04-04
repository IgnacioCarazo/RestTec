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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.models.HttpsTrustManager;
import com.example.restecmobile.models.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.List;
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
            /**
             * Crea el json de la factura e inicia el proceso del POST del pedido
             */
            public void onClick(View v) {
                JSONArray jsonFactura = new JSONArray();
                for (int i = 0; i < carroCompra.size(); i++) {
                    Producto pedido = carroCompra.get(i);
                    JSONObject jsonType= new JSONObject();
                    JSONObject jsonProduct = new JSONObject();
                    try {
                        jsonProduct.put("recipeName", pedido.getRecipeName());
                        jsonProduct.put("price", pedido.getPrice());
                        jsonProduct.put("calories", pedido.getCalories());
                        jsonProduct.put("prepareTime", pedido.getPrepareTime());
                        JSONObject jsonIngredientesPut = new JSONObject();
                        for(int k = 0; k< pedido.getIngredients().size();k++){
                            jsonIngredientesPut.put("name", pedido.getIngredients().get(k).getName());
                            jsonIngredientesPut.put("amount",pedido.getIngredients().get(k).getAmount());
                        }
                        jsonProduct.put("ingredients", jsonIngredientesPut);
                        jsonProduct.put("finishTime", pedido.getFinishTime());
                        jsonType.put("name",pedido.getType().getName());
                        jsonType.put("description",pedido.getType().getDescripcion());
                        jsonProduct.put("type", jsonType);
                        jsonFactura.put(jsonProduct);
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                jsonParse(jsonFactura);
                Intent intent = new Intent(CarroCompra.this, EsperaPedido.class);
                intent.putExtra("Espera de pedido", (Serializable) carroCompra);
                CarroCompra.this.startActivity(intent);
                CarroCompra.this.finish();
            }
        });
    }

    /**
     *  Por medio de un POST se hace el pedido y pasa a ventana de espera
     * @param jsonfactura
     */
    private void jsonParse(JSONArray jsonfactura){
        String postUrl = getString(R.string.URL_SOURCE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonPOST = new JSONObject();
        try {
            jsonPOST.put("orden",jsonfactura);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, jsonPOST, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CarroCompra.this, EsperaPedido.class);
                intent.putExtra("Espera de pedido", (Serializable) carroCompra);
                CarroCompra.this.startActivity(intent);
                CarroCompra.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();;
            }
        });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(jsonObjectRequest);
    }
}


