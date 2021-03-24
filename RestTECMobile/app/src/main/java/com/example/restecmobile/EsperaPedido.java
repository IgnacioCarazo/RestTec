package com.example.restecmobile;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Magnifier;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.login.LoginActivity;
import com.example.restecmobile.login.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.restecmobile.URLs.URL_Producto;
/**
 * @class EsperaPedido
 * Clase para mostrar el tiempo que falta para completar el pedido
 */
public class EsperaPedido extends AppCompatActivity {
    private TextView tvCantidadTiempo;
    private Button buttonFeedback;
    private RatingBar ratingBar;
    public int rate=0;
    public int time=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_pedido);
        tvCantidadTiempo = findViewById(R.id.tvCantidadTiempo);
        buttonFeedback =findViewById(R.id.botonFeedback);
        ratingBar=findViewById(R.id.ratingBar);
        jsonParseTime();
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
                    jsonParseFeedback();
                }
            }
        });
    }
    private void jsonParseTime(){String JSON_URL = URL_Producto;
        StringRequest stringRequestTime = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            time = jsonObject.getInt("Time");
                            tvCantidadTiempo.setText(time);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestTime);
    }
    private void jsonParseFeedback(){
        String JSON_URL = URL_Producto;
        Date fecha= Calendar.getInstance().getTime();
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
                        Intent principal = new Intent(EsperaPedido.this, MainActivity.class);
                        EsperaPedido.this.startActivity(principal);
                        EsperaPedido.this.finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Fecha", String.valueOf(fecha));
                params.put("Calificacion",String.valueOf(rate));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}