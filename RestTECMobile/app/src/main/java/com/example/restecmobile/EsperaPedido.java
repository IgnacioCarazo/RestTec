package com.example.restecmobile;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.models.HiloReady;
import com.example.restecmobile.models.HttpsTrustManager;
import com.example.restecmobile.models.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @class EsperaPedido
 * Clase para mostrar el tiempo que falta para completar el pedido
 */
public class EsperaPedido extends AppCompatActivity {
    public static boolean ready = false;
    public static String finishTime=null;
    public static Date startDateTime=null;
    public static TextView tvCantidadTiempo;
    private Button buttonFeedback;
    private RatingBar ratingBar;
    private int ordenID;
    private int clientID;
    public int rate=0;
    private List<Producto> carroCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_pedido);
        tvCantidadTiempo = findViewById(R.id.tvCantidadTiempo);
        buttonFeedback =findViewById(R.id.botonFeedback);
        ordenID = getIntent().getIntExtra("ordenID",0);
        clientID = getIntent().getIntExtra("clientID",0);
        carroCompra = (List<Producto>) getIntent().getSerializableExtra("CarroCompras");
        tvCantidadTiempo.setText("Aqui aparecera el tiempo de espera");
        ratingBar=findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = ((int) rating);
            }
        });
        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate >0 && ready == true) {
                    jsonParseFeedback(ordenID);
                }
            }
        });
        checkParse();
    }
    public void checkParse(){
        HiloReady hilo=new HiloReady(ordenID,EsperaPedido.this);
        Thread nuevoh=new Thread(hilo);
        nuevoh.start();
        startDateTime = Calendar.getInstance().getTime();
    }

    /**
     *  Por medio de un POST permite enviar el feedback y volver a la ventana principal
     * @param orderID
     */
    private void jsonParseFeedback(int orderID){
        String postUrl =getString(R.string.URL_SOURCE)+"api/Report/feedBack";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            JSONArray namesIngredients = new JSONArray();
            for (int i = 0; i <carroCompra.size(); i++) {
                Producto pedido = carroCompra.get(i);
                String pedidoNombre = pedido.getRecipeName();
                namesIngredients.put(pedidoNombre);
            }
            postData.put("recipeName", namesIngredients);
            postData.put("recipeStars", rate);
            postData.put("feedBackDate", startDateTime);
            postData.put("clientID", clientID);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        String requestBody = postData.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent principal = new Intent(EsperaPedido.this, MainActivity.class);
                EsperaPedido.this.startActivity(principal);
                EsperaPedido.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return String.format("application/json; charset=utf-8");
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }
        };
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(stringRequest);
    }
}