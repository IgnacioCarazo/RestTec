package com.example.restecmobile;
import java.io.Serializable;
/**
 * @class RecipeType
 * Clase constructura de Tipo de receta
 * @author JosephJ
 */
public class RecipeType implements Serializable {
    private String name;
    private String descripcion;
    public RecipeType(String name, String descripcion){ this.name=name;this.descripcion=descripcion; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
