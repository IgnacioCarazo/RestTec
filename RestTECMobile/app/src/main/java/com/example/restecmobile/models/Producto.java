package com.example.restecmobile.models;
import java.io.Serializable;
import java.util.List;
/**
 * @class Producto
 * Clase constructura de productos
 * @author JosephJ
 */
public class Producto implements Serializable {
    private String recipeName;
    private int price;
    private int calories;
    private int prepareTime;
    private List<Ingredient> ingredients;
    private String finishTime;
    private RecipeType type;
    public Producto(String recipeName , int precio , int calories , int prepareTime , List<Ingredient> ingredients , String finishTime, RecipeType type){
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.finishTime = finishTime;
        this.calories = calories;
        this.prepareTime = prepareTime;
        this.price = precio;
        this.type = type;
    }
    public String getRecipeName() { return recipeName; }
    public void setRecipeName(String recipeName) { this.recipeName = recipeName; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    public int getPrepareTime() { return prepareTime; }
    public void setPrepareTime(int prepareTime) { this.prepareTime = prepareTime; }

    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }

    public String getFinishTime() { return finishTime; }
    public void setFinishTime(String finishTime) { this.finishTime = finishTime; }

    public RecipeType getType() { return type; }
    public void setType(RecipeType type) { this.type = type; }
}
