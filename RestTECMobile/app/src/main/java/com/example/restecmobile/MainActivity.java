package com.example.restecmobile;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCantProductos = findViewById(R.id.tvCantProductos);
        btnVerCarro = findViewById(R.id.btnVerCarro);
        rvListaProductos = findViewById(R.id.rvListaProductos);
        rvListaProductos.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
        //parseJSON();
        Ingredient Ingrediente = new Ingredient("arroz",1);
        Ingredient Ingrediente1 = new Ingredient("carne",2);
        Ingredient Ingrediente2 = new Ingredient("vegetales",3);
        Ingredient Ingrediente3 = new Ingredient("agua",10);
        ArrayList listaIngrediets = new ArrayList();
        listaIngrediets.add(Ingrediente);
        listaIngrediets.add(Ingrediente1);
        listaIngrediets.add(Ingrediente2);
        listaIngrediets.add(Ingrediente3);
        RecipeType recipeType1 = new RecipeType("almuerzo","Almuerzo a la carte");
        listaProductos.add(new Producto("Gallo Pinto",2200,300,-1,listaIngrediets,"10:00",recipeType1));
        listaProductos.add(new Producto("Casado",2100,500,-1,listaIngrediets,"11:20",recipeType1));
        listaProductos.add(new Producto("Sopa de Mondongo",3300,600,-1,listaIngrediets,"10:00",recipeType1));
        listaProductos.add(new Producto("Porcion de pollo",2100,300,-1,listaIngrediets,"10:50",recipeType1));
        listaProductos.add(new Producto("Tortilla con queso",1200,400,-1,listaIngrediets,"11:00",recipeType1));
        listaProductos.add(new Producto("Hamburguesa y papas",3200,500,-1,listaIngrediets,"9:00",recipeType1));
        adaptador = new AdaptadorProductos(MainActivity.this, tvCantProductos,btnVerCarro,listaProductos,carroCompras);
        rvListaProductos.setAdapter(adaptador);
    }
    /**
     * Por medio de un GET se hace la solicitud de los platillos disponibles
     */
    private void parseJSON(){
        String postUrl = "http://localhost:5001";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, postUrl , null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject obj = response;
                    JSONArray jsonArray = obj.getJSONArray("recipes");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject productos = jsonArray.getJSONObject(i);
                        JSONArray jsonIngredientes = obj.getJSONArray("ingredientes");
                        List<Ingredient> descripcionIngredientes = null;
                        for(int k=0; k< jsonIngredientes.length();k++){
                            JSONObject ingredienteHeader = jsonArray.getJSONObject(i);
                            String ingredientName = ingredienteHeader.getString("name");
                            int ingredientAmount = ingredienteHeader.getInt("amount");
                            Ingredient ingredient = new Ingredient(ingredientName,ingredientAmount);
                            descripcionIngredientes.add(ingredient);
                        }
                        RecipeType recipeType = new RecipeType(productos.getString("name"),productos.getString("descripcion"));
                        Producto producto = new Producto(productos.getString("recipeName"),productos.getInt("price"),productos.getInt("calories"),productos.getInt("prepareTime"),descripcionIngredientes,productos.getString("finishTime"),recipeType);
                        listaProductos.add(producto);
                    }
                    adaptador = new AdaptadorProductos(MainActivity.this, tvCantProductos,btnVerCarro,listaProductos,carroCompras);
                    rvListaProductos.setAdapter(adaptador);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}