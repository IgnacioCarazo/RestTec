package com.example.restecmobile.models;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.example.restecmobile.EsperaPedido.ready;
import static com.example.restecmobile.EsperaPedido.finishTime;
import static com.example.restecmobile.EsperaPedido.startDateTime;
import static com.example.restecmobile.EsperaPedido.tvCantidadTiempo;
/**
 * @class HiloReady
 * Hilo que sirve para verificar si la hora esta lista
 * @author JosephJ
 */
public class HiloReady implements Runnable {
    int orderID;
    Context context;
    public HiloReady(int orderId, Context context){  this.orderID=orderId;this.context=context;}
    public void run(){
        while(ready==false){
            try {
                parseJSONReady(orderID);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *  Por medio de un GET obtiene si este ya fue tomado por un chef
     */
    private void parseJSONReady(int ordenID){
        String getUrl = "https://10.0.2.2:5001/api/Order/verify/"+ordenID;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("True")){
                            ready=true;
                            parseTime(ordenID);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERRORVOLLEYREADY: " + error);
            }
        });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(stringRequest);
    }
    /**
     *  Por medio de un GET obtiene  el tiempo de espera
     */
    private void parseTime(int ordenID){
        String getUrl = "https://10.0.2.2:5001/api/Order/"+ordenID;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, getUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("mm:ss");
                        try {
                            JSONArray recipeIncluded = response.getJSONArray("recipeIncluded");
                            JSONObject jsonObject = recipeIncluded.getJSONObject(0);
                            finishTime = jsonObject.getString("finishTime");
                            Date finishDateTime= null;
                            try {
                                finishDateTime = formatoDelTexto.parse(finishTime);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long totaltime = finishDateTime.getTime() - startDateTime.getTime();
                            tvCantidadTiempo.setText((formatoDelTexto.format(totaltime))+" min");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("VolleyError"+error.toString());
                    }
                });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(jsonObjectRequest);
    }
}