package com.example.restecmobile;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.restecmobile.models.Producto;
import java.util.List;
/**
 * @class AdaptadorCarroCompras
 * Clase crea el contexto, la vista del total, lista de productos
 * @author JosephJ
 */
public class AdaptadorCarroCompra extends RecyclerView.Adapter<AdaptadorCarroCompra.ProductosViewHolder> {
    Context context;
    List<Producto> carroCompra;
    TextView tvTotal;
    double total = 0;
    public AdaptadorCarroCompra(Context context, List<Producto> carroCompra, TextView tvTotal) {
        this.context = context;
        this.carroCompra = carroCompra;
        this.tvTotal = tvTotal;
        for (int i = 0; i < carroCompra.size(); i++) {
            total = total + Double.parseDouble("" + carroCompra.get(i).getPrice());
        }
        tvTotal.setText("" + total);
    }
    /**
     * @param viewGroup el cual se añade a ProductosViewHolder del carro de Compras
     * @param i
     * @return el adaptador de productos al carro de Compras
     */
    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_carro_compras, null, false);
        return new AdaptadorCarroCompra.ProductosViewHolder(v);
    }
    /**
     * Obtiene los productos del carroCompra y sus atributos
     */
    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, final int i) {
        productosViewHolder.tvNomProducto.setText(carroCompra.get(i).getRecipeName());
        String descripcionIngredientes = "";
        for(int k=0; k<carroCompra.get(i).getIngredients().size();k++){
            String ingredientName = carroCompra.get(i).getIngredients().get(k).getName();
            int ingredientAmount = carroCompra.get(i).getIngredients().get(k).getAmount();
            descripcionIngredientes = descripcionIngredientes+ "Ingrediente: "+ ingredientName +", cantidad: " + ingredientAmount+ ", ";
        }
        productosViewHolder.tvDescripcion.setText(descripcionIngredientes);
        productosViewHolder.tvPrecio.setText(" " + carroCompra.get(i).getPrice());
    }
    /**
     * @return tamaño de lista de productos
     */
    @Override
    public int getItemCount() {
        return carroCompra.size();
    }
    /**
     * @class ProductosViewHolder
     * Extiende de ReciclerView, obtiene los valores de la Vista del Carro Compra
     */
    public class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomProducto, tvDescripcion, tvPrecio;
        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}