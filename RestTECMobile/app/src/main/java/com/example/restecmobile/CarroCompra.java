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
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;
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
            public void onClick(View v) {
                for(int i = 0 ;i  < carroCompra.size(); i++){
                    Producto pedido = carroCompra.get(i);
                    String recipeName = pedido.getRecipeName();
                    double price = pedido.getPrice();
                    Intent intent = new Intent(CarroCompra.this, EsperaPedido.class);
                    intent.putExtra("Espera de pedido", (Serializable) carroCompra);
                    CarroCompra.this.startActivity(intent);
                    CarroCompra.this.finish();
                    //jsonParse(nombrePedido,detallePedido);
                }
            }
        });
    }
    private void jsonParse(String recipeName, double price){
        String postUrl = "http://localhost:5001";
        List listavacia = new ArrayList();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("recipeName", recipeName);
            postData.put("price", price);
            postData.put("calories", 0000000);
            postData.put("prepareTime", 0000000);
            postData.put("ingredients", listavacia);
            postData.put("finishTime", "00:00");
            postData.put("type", listavacia);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
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
        requestQueue.add(jsonObjectRequest);
    }
}


