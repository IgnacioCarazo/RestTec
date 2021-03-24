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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.MainActivity;
import com.example.restecmobile.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import static com.example.restecmobile.URLs.URL_Usuario;
/**
 * @class LoginActivity
 * Crea el login, busca los ids de la vista, obtiene sus botones y da la funcion al dar click
 * @author JosephJ
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registro = (TextView)findViewById(R.id.textViewRegistro);
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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            /**
             * Se obtiene los valores insertados por usuario en Login, crea un listener para la respuesta del request
             * obtiene data de la respuesta y abre la vista principal
             * @param v
             */
            @Override
            public void onClick(View v) {
                final String usuario = usuarioT.getText().toString();
                final String clave = claveT.getText().toString();
                //jsonParse(usuario,clave);
                Intent principal = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(principal);
                LoginActivity.this.finish();
            }
        });
    }
    /**
     * Manejo del solicitud POST Rest por parte del login
     */
    private void jsonParse(String correo, String clave){
        String JSON_URL = URL_Usuario;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if(!object.getBoolean("success")){
                                Intent principal = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(principal);
                                LoginActivity.this.finish();
                            }else {
                                Toast.makeText(getApplicationContext(),"User Login UnSuccessFull", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        /*
                        Intent principal = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(principal);
                        LoginActivity.this.finish();
                        */
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String>parms=new HashMap<String, String>();
                parms.put("email",correo);
                parms.put("password",clave);
                return parms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}