package com.example.restecmobile;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.io.Serializable;
import java.util.List;
/**
 * @class AdaptadorProductos
 * Clase que sirve para crear el contexto, la vista del texto de Cantidad productos, el boton para ver el carro, listaProductos y el carroCompra
 * @author JosephJ
 */
public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {
    Context context;
    TextView tvCantProductos;
    Button btnVerCarro;
    List<Producto> listaProductos;
    List<Producto> carroCompra;
    public AdaptadorProductos(Context context, TextView tvCantProductos, Button btnVerCarro, List<Producto> listaProductos, List<Producto> carroCompra) {
        this.context = context;
        this.tvCantProductos = tvCantProductos;
        this.btnVerCarro = btnVerCarro;
        this.listaProductos = listaProductos;
        this.carroCompra = carroCompra;
    }
    /**
     * @param viewGroup Crea una vista a partir de este parametro e inserta el Layout item_rv_products
     * @param i
     * @return el adaptador de productos con la vista especificada
     */
    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_products, null, false);
        return new AdaptadorProductos.ProductosViewHolder(v);
    }
    /**
     * @param productosViewHolder al cual se le agregan los productos seleccionados
     * @param i
     * Escucha cuando se cambia el cuadro de seleccion y habilita el boton para abrir carro de compras
     */
    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, final int i) {
        productosViewHolder.tvNomProducto.setText(listaProductos.get(i).getRecipeName());
        String Ingredientes = "";
        for(int k=0; k<listaProductos.get(i).getIngredients().size();k++){
            String ingredientName = listaProductos.get(i).getIngredients().get(k).getName();
            int ingredientAmount = listaProductos.get(i).getIngredients().get(k).getAmount();
            Ingredientes = Ingredientes + "Ingrediente: "+ ingredientName +", cantidad: " + ingredientAmount+ ", ";
        }
        productosViewHolder.tvIngredientes.setText(Ingredientes);
        productosViewHolder.tvPrecio.setText("" + listaProductos.get(i).getPrice() + " colones");
        productosViewHolder.tvCalories.setText("" + listaProductos.get(i).getCalories() + " calorias");
        productosViewHolder.tvPrepareTime.setText("Tiempo de preparacion: " + listaProductos.get(i).getPrepareTime() );
        productosViewHolder.cbCarro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (productosViewHolder.cbCarro.isChecked() == true) {
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) + 1));
                    carroCompra.add(listaProductos.get(i));
                } else if (productosViewHolder.cbCarro.isChecked() == false) {
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) - 1));
                    carroCompra.remove(listaProductos.get(i));
                }
            }
        });
        btnVerCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CarroCompra.class);
                intent.putExtra("CarroCompras", (Serializable) carroCompra);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listaProductos.size();
    }
    /**
     * @class ProductosViewHolder
     * Extiende de ReciclerView, obtiene los valores de la Vista del producto
     */
    public class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomProducto, tvIngredientes, tvPrecio, tvCalories, tvPrepareTime;
        CheckBox cbCarro;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvIngredientes = itemView.findViewById(R.id.tvIngredientes);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvCalories = itemView.findViewById(R.id.tvCalories);
            tvPrepareTime = itemView.findViewById(R.id.tvPrepareTime);
            cbCarro = itemView.findViewById(R.id.cbCarro);
        }
    }
}
