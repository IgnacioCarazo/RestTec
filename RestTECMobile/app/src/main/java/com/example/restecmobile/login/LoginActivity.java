package com.example.restecmobile.login;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.models.HttpsTrustManager;
import com.example.restecmobile.MainActivity;
import com.example.restecmobile.R;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * @class LoginActivity
 * Crea el login, busca los ids de la vista, obtiene sus botones y les agrega su funcion
 * @author JosephJ
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registro = (TextView)findViewById(R.id.textViewRegistro);
        TextView eliminar = (TextView)findViewById(R.id.textViewEliminar);
        Button btnLogin = (Button)findViewById(R.id.buttonLogin);
        final EditText  usuarioT = (EditText)findViewById(R.id.loginUser);
        final EditText  claveT = (EditText)findViewById(R.id.loginPassword);
        registro.setOnClickListener(new View.OnClickListener() {
            /**
             * Al click en Registro se abre la vista que permite registrar
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registro);
                LoginActivity.this.finish();
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            /**
             * Al click set obtiene el id y contrasena para eliminar
             * @param v
             */
            @Override
            public void onClick(View v) {
                final String usuario = usuarioT.getText().toString();
                parseJSONEliminar(usuario);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            /**
             * Se obtiene los valores insertados por usuario en Login, crea un listener para esperar la respuesta del
             * request REST y abre la vista principal
             * @param v
             */
            @Override
            public void onClick(View v) {
                final String usuario = usuarioT.getText().toString();
                final String clave = claveT.getText().toString();
                parseJSONLogin(usuario,clave);
            }
        });
    }
    /**
     * Manejo de la solicitud GET por parte del login
     */
    private void parseJSONLogin(String correo,String clave){
        String getUrl = getString(R.string.URL_SOURCE)+"api/Client/login/"+correo+getString(R.string.slash)+clave;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, getUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String acceso = "No client found or Access denied";
                        if(response.toString().contains(acceso)){
                            Toast.makeText(getApplicationContext(),"User Login UnSuccessFull", Toast.LENGTH_LONG).show();
                        }else {
                            try {
                                Toast.makeText(getApplicationContext(),"Login SuccessFull", Toast.LENGTH_LONG).show();
                                Intent principal = new Intent(LoginActivity.this, MainActivity.class);
                                int clientID = response.getInt("iD");
                                principal.putExtra("clientID",clientID);
                                LoginActivity.this.startActivity(principal);
                                LoginActivity.this.finish();
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"User Login UnSuccessFull", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"User Login UnSuccessFull", Toast.LENGTH_LONG).show();
                    }
                });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(jsonObjectRequest);
    }
    /**
     * Manejo de la solicitud DELETE por parte del login
     */
    private void parseJSONEliminar(String ID){
        String getUrl = getString(R.string.URL_SOURCE)+"api/Client/delete"+getString(R.string.slash)+ID;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, getUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String acceso = "Cliente Deleted Successully";
                        if(response.toString().contains(acceso)){
                            Toast.makeText(getApplicationContext(),"User Removed SuccessFull", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"User Removed UnSuccessFull", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Remove UnsuccessFully", Toast.LENGTH_LONG).show();
            }
        });
        HttpsTrustManager.allowAllSSL();
        requestQueue.add(stringRequest);
    }
}