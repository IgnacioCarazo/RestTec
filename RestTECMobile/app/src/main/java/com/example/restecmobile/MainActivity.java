package com.example.restecmobile;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restecmobile.models.Ingredient;
import com.example.restecmobile.models.Producto;
import com.example.restecmobile.models.RecipeType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
/**
 * @class MainActivity
 * En esta clase se habilitan los productos posibles a seleccionar asi como el boton que cofirma en el envio de estos
 * @author JosephJ
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvCantProductos;
    private Button btnVerCarro;
    private RecyclerView rvListaProductos;
    private AdaptadorProductos adaptador;
    private List<Producto> listaProductos = new ArrayList<>();
    private List<Producto> carroCompras = new ArrayList<>();
    private int clientID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientID = getIntent().getIntExtra("clientID",0);
        tvCantProductos = findViewById(R.id.tvCantProductos);
        btnVerCarro = findViewById(R.id.btnVerCarro);
        rvListaProductos = findViewById(R.id.rvListaProductos);
        rvListaProductos.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        parseJSON();
    }
    /**
     * Por medio de un GET se hace la solicitud de los platillos disponibles
     */
    private void parseJSON(){
        String getUrl = getString(R.string.URL_SOURCE) + "api/Recipe";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayreq = new JsonArrayRequest(getUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray recipes = response;
                            System.out.println(response);
                            for (int i = 0; i <recipes.length(); i++) {
                                JSONObject platillo = recipes.getJSONObject(i);
                                JSONArray jsonIngredientes = platillo.getJSONArray("ingredients");
                                List<Ingredient> descripcionIngredientes = new ArrayList<Ingredient>();
                                RecipeType tipoReceta = new RecipeType();
                                for (int k = 0; k < jsonIngredientes.length(); k++) {
                                    JSONObject valuesIngredientes = jsonIngredientes.getJSONObject(k);
                                    String ingredientName = valuesIngredientes.getString("name");
                                    int ingredientAmount = valuesIngredientes.getInt("amount");
                                    Ingredient ingredient = new Ingredient(ingredientName, ingredientAmount);
                                    descripcionIngredientes.add(ingredient);
                                }
                                JSONObject jsonType = platillo.getJSONObject("type");
                                String typoName = jsonType.getString("name");
                                String typoDescription = jsonType.getString("description");
                                tipoReceta.setName(typoName);
                                tipoReceta.setDescripcion(typoDescription);
                                Producto producto = new Producto(platillo.getString("recipeName"), platillo.getInt("price"), platillo.getInt("calories"), platillo.getInt("prepareTime"), descripcionIngredientes, platillo.getString("finishTime"), tipoReceta);
                                listaProductos.add(producto);
                            }
                            adaptador = new AdaptadorProductos(MainActivity.this, tvCantProductos,btnVerCarro,listaProductos,carroCompras,clientID);
                            rvListaProductos.setAdapter(adaptador);
                        } catch (JSONException e) {
                            System.out.println("ERROR"+e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(arrayreq);
    }
}