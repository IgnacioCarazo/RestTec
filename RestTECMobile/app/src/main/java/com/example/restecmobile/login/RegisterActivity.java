package com.example.restecmobile.login;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
/**
 * @class RegisterActivity
 * Crea los cuadros de registro, obtiene la informacion digitada por el usuario. crea escuchador de respuesta para cuando se haga la solicitud al rest
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
        Button btnRegistro = (Button)findViewById(R.id.buttonRegister);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = cedulaT.getText().toString();
                String nombre = nombreT.getText().toString();
                String primerApellido = primerApellidoT.getText().toString();
                String segundoApellido = segundoApellidoT.getText().toString();
                String provincia = provinciaT.getText().toString();
                String canton = cantonT.getText().toString();
                String distrito = distritoT.getText().toString();
                String nacimiento = nacimientoT.getText().toString();
                String telefono = telefonoT.getText().toString();
                String correo = correoT.getText().toString();
                String contrasena = contrasenaT.getText().toString();
                //jsonParse(cedula,nombre,primerApellido,segundoApellido,provincia,canton,distrito,nacimiento,telefono,correo,contrasena);
                Intent principal = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(principal);
                RegisterActivity.this.finish();
            }
        });
    }
    /**
     * Manejo del solicitud Rest por parte del Registro
     */
    private void jsonParse(String cedula, String nombre, String primerApellido, String segundoApellido, String provincia, String canton, String distrito, String nacimiento, String telefono, String correo, String clave){
        String postUrl = "http://localhost:5001/";
        List listavacia = new ArrayList();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("Cedula",cedula);
            postData.put("Nombre",nombre);
            postData.put("Primer Apellido",primerApellido);
            postData.put("Segundo Apellido",segundoApellido);
            postData.put("Provincia",provincia);
            postData.put("Canton",canton);
            postData.put("Distrito",distrito);
            postData.put("Nacimiento",nacimiento);
            postData.put("Telefono",telefono);
            postData.put("Correo",correo);
            postData.put("Clave",clave);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
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
        requestQueue.add(jsonObjectRequest);
    }
}