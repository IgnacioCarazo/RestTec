package com.example.restecmobile.models;
import java.io.Serializable;
/**
 * @class Ingredient
 * Clase constructura de Tipo de Ingredientes
 * @author JosephJ
 */
public class Ingredient implements Serializable {
    private String name;
    private int amount;
    public Ingredient(String name, int amount){ this.name=name;this.amount=amount; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
}
