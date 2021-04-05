package com.example.restecmobile;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.models.HttpsTrustManager;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * @class EsperaPedido
 * Clase para mostrar el tiempo que falta para completar el pedido
 */
public class EsperaPedido extends AppCompatActivity {
    private TextView tvCantidadTiempo;
    private Button buttonFeedback;
    private RatingBar ratingBar;
    public int rate=0;
    int time=0;
    public int orderID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_pedido);
        tvCantidadTiempo = findViewById(R.id.tvCantidadTiempo);
        buttonFeedback =findViewById(R.id.botonFeedback);
        Intent i = this.getIntent();
        //parseJSONTime();
        tvCantidadTiempo.setText(time+" mins");
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
                if(rate >0 && time==0) {
                    //jsonParseFeedback(orderID);
                    Intent principal = new Intent(EsperaPedido.this, MainActivity.class);
                    EsperaPedido.this.startActivity(principal);
                    EsperaPedido.this.finish();
                }
            }
        });
    }
    /**
     *  Por medio de un GET obtiene el tiempo para terminar y el id de la orden
     */
    private void parseJSONTime(){
        String getUrl = getString(R.string.URL_SOURCE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, getUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            time = response.getInt("finisthTime");
                            orderID = response.getInt("ID");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(jsonObjectRequest);
    }
    /**
     *  Por medio de un POST permite enviar el feedback y volver a la ventana principal
     * @param orderID
     */
    private void jsonParseFeedback(int orderID){
        String postUrl =getString(R.string.URL_SOURCE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("orderId", orderID);
            postData.put("feedback", String.valueOf(rate));
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent principal = new Intent(EsperaPedido.this, MainActivity.class);
                EsperaPedido.this.startActivity(principal);
                EsperaPedido.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(jsonObjectRequest);
    }
}