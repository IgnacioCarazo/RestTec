package com.example.restecmobile.login;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.EsperaPedido;
import com.example.restecmobile.MainActivity;
import com.example.restecmobile.models.Cliente;
import com.example.restecmobile.models.HttpsTrustManager;
import com.example.restecmobile.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * @class RegisterActivity
 * Crea los cuadros de registro, obtiene la informacion digitada por el usuario. crea escucha
 * de respuesta para solicitud de REST
 * @author JosephJ
 */
public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText cedulaT = (EditText) findViewById(R.id.registerIDUser);
        EditText nombreT = (EditText) findViewById(R.id.registerNameUser);
        EditText primerApellidoT = (EditText) findViewById(R.id.registerFLastNameUser);
        EditText segundoApellidoT = (EditText) findViewById(R.id.registerSLastNameUser);
        EditText provinciaT = (EditText) findViewById(R.id.registerProvinceUser);
        EditText cantonT = (EditText) findViewById(R.id.registerCantonUser);
        EditText distritoT = (EditText) findViewById(R.id.registerDistrictUser);
        EditText nacimientoT = (EditText) findViewById(R.id.registerBirthUser);
        EditText telefonoT = (EditText) findViewById(R.id.registerTelephoneUser);
        EditText correoT = (EditText) findViewById(R.id.registerEmailUser);
        EditText contrasenaT = (EditText) findViewById(R.id.registerPasswordUser);
        Button btnUpdate= (Button)findViewById(R.id.buttonUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedulaString =cedulaT.getText().toString();
                int cedula = Integer.parseInt(cedulaString);
                String nombre = nombreT.getText().toString();
                String primerApellido = primerApellidoT.getText().toString();
                String segundoApellido = segundoApellidoT.getText().toString();
                String provincia = provinciaT.getText().toString();
                String canton = cantonT.getText().toString();
                String distrito = distritoT.getText().toString();
                String nacimiento = nacimientoT.getText().toString();
                String telefonoString = telefonoT.getText().toString();
                int telefono = Integer.parseInt(telefonoString);
                String correo = correoT.getText().toString();
                String contrasena = contrasenaT.getText().toString();
                Cliente client = new Cliente();
                try{
                    client.setID(cedula);
                    client.setName(nombre);
                    client.setPrimaryLastName(primerApellido);
                    client.setSecondLastName(segundoApellido);
                    client.setProvince(provincia);
                    client.setCanton(canton);
                    client.setDistrito(distrito);
                    client.setBirthday(nacimiento);
                    client.setCelNum(telefono);
                    client.setEmail(correo);
                    client.setPassword(contrasena);
                    jsonParse(client);
                }catch(Exception e){
                    System.out.println("ERROR creando cliente Update: "+e);
                }
            }
        });
        Button btnRegistro= (Button)findViewById(R.id.buttonRegister);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedulaString =cedulaT.getText().toString();
                int cedula = Integer.parseInt(cedulaString);
                String nombre = nombreT.getText().toString();
                String primerApellido = primerApellidoT.getText().toString();
                String segundoApellido = segundoApellidoT.getText().toString();
                String provincia = provinciaT.getText().toString();
                String canton = cantonT.getText().toString();
                String distrito = distritoT.getText().toString();
                String nacimiento = nacimientoT.getText().toString();
                String telefonoString = telefonoT.getText().toString();
                int telefono = Integer.parseInt(telefonoString);
                String correo = correoT.getText().toString();
                String contrasena = contrasenaT.getText().toString();
                Cliente client = new Cliente();
                try{
                    client.setID(cedula);
                    client.setName(nombre);
                    client.setPrimaryLastName(primerApellido);
                    client.setSecondLastName(segundoApellido);
                    client.setProvince(provincia);
                    client.setCanton(canton);
                    client.setDistrito(distrito);
                    client.setBirthday(nacimiento);
                    client.setCelNum(telefono);
                    client.setEmail(correo);
                    client.setPassword(contrasena);
                    jsonParseRegister(client);
                }catch(Exception e){
                    System.out.println("ERROR creando cliente registro: "+e);
                }
            }
        });
    }
    /**
     * Manejo del solicitud Rest por parte del Update
     */
    private void jsonParse(Cliente client){
        String postUrl = getString(R.string.URL_SOURCE)+"api/Client/update";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject putData = new JSONObject();
        try {
            putData.put("ID",client.getID());
            putData.put("name",client.getName());
            putData.put("primaryLastName",client.getPrimaryLastName());
            putData.put("secondLastName",client.getSecondLastName());
            putData.put("province",client.getProvince());
            putData.put("canton",client.getCanton());
            putData.put("district",client.getDistrito());
            putData.put("birthday",client.getBirthday());
            putData.put("celNum",client.getCelNum());
            putData.put("email",client.getEmail());
            putData.put("password",client.getPassword());
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        String requestBody = putData.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, postUrl,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String acceso = "Client Updated";
                if(response.toString().contains(acceso)){
                    Toast.makeText(getApplicationContext(),"UPDATE SuccessFull", Toast.LENGTH_LONG).show();
                    Intent principal = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(principal);
                    RegisterActivity.this.finish();
                }else {
                    Toast.makeText(getApplicationContext(),"User UPDATE UnSuccessFull", Toast.LENGTH_LONG).show();
                }
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
    /**
     * Manejo del solicitud Rest por parte del Registro
     */
    private void jsonParseRegister(Cliente client){
        String postUrl = getString(R.string.URL_SOURCE)+"api/Client/newClient/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("ID",client.getID());
            postData.put("name",client.getName());
            postData.put("primaryLastName",client.getPrimaryLastName());
            postData.put("secondLastName",client.getSecondLastName());
            postData.put("province",client.getProvince());
            postData.put("canton",client.getCanton());
            postData.put("district",client.getDistrito());
            postData.put("birthday",client.getBirthday());
            postData.put("celNum",client.getCelNum());
            postData.put("email",client.getEmail());
            postData.put("password",client.getPassword());
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent principal = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(principal);
                RegisterActivity.this.finish();
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