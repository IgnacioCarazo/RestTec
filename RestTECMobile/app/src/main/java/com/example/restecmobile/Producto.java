package com.example.restecmobile;
import java.io.Serializable;
/**
 * @class Producto
 * Clase constructura de productos
 * @author JosephJ
 */
public class Producto implements Serializable {
    private String ID_PRODUCTO;
    private String NOM_PRODUCTO;
    private String DESCRIPCION;
    private double PRECIO;
    public Producto(String idProducto, String nomProducto, String descripcion, double precio){
        this.ID_PRODUCTO = idProducto;
        this.NOM_PRODUCTO = nomProducto;
        this.DESCRIPCION = descripcion;
        this.PRECIO = precio;
    }
    public String getID_PRODUCTO() { return ID_PRODUCTO; }
    public void setID_PRODUCTO(String ID_PRODUCTO) { this.ID_PRODUCTO = ID_PRODUCTO; }

    public String getNOM_PRODUCTO() { return NOM_PRODUCTO; }
    public void setNOM_PRODUCTO(String NOM_PRODUCTO) { this.NOM_PRODUCTO = NOM_PRODUCTO; }

    public String getDESCRIPCION() { return DESCRIPCION; }
    public void setDESCRIPCION(String DESCRIPCION) { this.DESCRIPCION = DESCRIPCION; }

    public double getPRECIO() { return PRECIO; }
    public void setPRECIO(double PRECIO) { this.PRECIO = PRECIO; }
}