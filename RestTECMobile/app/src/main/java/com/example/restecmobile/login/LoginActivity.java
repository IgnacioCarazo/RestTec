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
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.MainActivity;
import com.example.restecmobile.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.Date;
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
    private void parseJSON(String correo,String clave){
        String postUrl = getString(R.string.URL_SOURCE)+correo+getString(R.string.slash)+clave;
        Date fecha= Calendar.getInstance().getTime();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, postUrl , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject object=response;
                            if(!object.getBoolean("success")){
                                Intent principal = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(principal);
                                LoginActivity.this.finish();
                            }else {
                                Toast.makeText(getApplicationContext(),"User Login UnSuccessFull", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}