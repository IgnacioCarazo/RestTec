package com.example.restecmobile;
import java.io.Serializable;
/**
 * @class Producto
 * Clase constructura de productos
 * @author JosephJ
 */
public class Producto implements Serializable {
    private String recipeName;
    private String ingredients;
    private int calories;
    private int prepareTime;
    private double price;
    public Producto(String recipeName, String ingredients, int calories, int prepareTime, double precio){
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.calories = calories;
        this.prepareTime = prepareTime;
        this.price = precio;
    }
    public String getRecipeName() { return recipeName; }
    public void setRecipeName(String recipeName) { this.recipeName = recipeName; }

    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public int getCalories() { return calories;}
    public void setCalories(int calories) { this.calories = calories; }

    public int getPrepareTime() { return prepareTime; }
    public void setPrepareTime(int prepareTime) { this.prepareTime = prepareTime; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
