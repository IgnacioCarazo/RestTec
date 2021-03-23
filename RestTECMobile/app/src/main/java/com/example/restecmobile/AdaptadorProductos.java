package com.example.restecmobile;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import java.util.List;
/**
 * @class AdaptadorProductos
 * Clase crea el contexto, la vista de Cantidad productos, listaProductos y ListacarroCompra y el boton para ver el carro,
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
     * @param viewGroup el cual se añade a ProductosViewHolder
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
        productosViewHolder.tvNomProducto.setText(listaProductos.get(i).getNOM_PRODUCTO());
        productosViewHolder.tvDescripcion.setText(listaProductos.get(i).getDESCRIPCION());
        productosViewHolder.tvPrecio.setText(""+listaProductos.get(i).getPRECIO());
        productosViewHolder.cbCarro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(productosViewHolder.cbCarro.isChecked() == true) {
                    tvCantProductos.setText(""+(Integer.parseInt(tvCantProductos.getText().toString().trim()) + 1));
                    carroCompra.add(listaProductos.get(i));
                } else if(productosViewHolder.cbCarro.isChecked() == false)        {
                    tvCantProductos.setText(""+(Integer.parseInt(tvCantProductos.getText().toString().trim()) - 1));
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
    /**
     * @return tamaño de lista de productos
     */
    @Override
    public int getItemCount() { return listaProductos.size(); }
    /**
     * @class ProductosViewHolder
     * Extiende de ReciclerView, obtiene los valores de la Vista del producto
     */
    public class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomProducto, tvDescripcion, tvPrecio;
        CheckBox cbCarro;
        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            cbCarro = itemView.findViewById(R.id.cbCarro);
        }
    }
}