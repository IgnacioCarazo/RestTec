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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.R;
import java.util.HashMap;
import java.util.Map;
import static com.example.restecmobile.URLs.URL_Usuario;
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
                jsonParse(cedula,nombre,primerApellido,segundoApellido,provincia,canton,distrito,nacimiento,telefono,correo,contrasena);
            }
        });
    }
    /**
     * Manejo del solicitud Rest por parte del Registro
     */
    private void jsonParse(String cedula, String nombre, String primerApellido, String segundoApellido, String provincia, String canton, String distrito, String nacimiento, String telefono, String correo, String clave){
        String JSON_URL = URL_Usuario;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        /*
                        try {
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                        Intent principal = new Intent(RegisterActivity.this, LoginActivity.class);
                        RegisterActivity.this.startActivity(principal);
                        RegisterActivity.this.finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Cedula",cedula);
                params.put("Nombre",nombre);
                params.put("Primer Apellido",primerApellido);
                params.put("Segundo Apellido",segundoApellido);
                params.put("Provincia",provincia);
                params.put("Canton",canton);
                params.put("Distrito",distrito);
                params.put("Nacimiento",nacimiento);
                params.put("Telefono",telefono);
                params.put("Correo",correo);
                params.put("Clave",clave);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}