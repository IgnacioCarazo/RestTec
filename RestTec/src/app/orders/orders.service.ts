import { Injectable } from "@angular/core";
import { RecipeType } from "../recipe-type/recipe-type.model";
import { Recipe } from "../recipes/recipe.model";
import { Ingredient } from "../shared/ingredient.model";
import { Order } from "./order.model";

@Injectable()
export class OrdersService {
    private allOrders = [
        new Order(135, true, "El Mejor Chef Ignacio Carazo", [
            new Recipe("Pizza", 5000, 500, "",[new Ingredient("Pepperoni",3)], new RecipeType("Almuerzo",""))], 5)
    ];
    private assignedOrders: Order[];
    private unassignedOrders: Order[];

    
    getAllOrders() {
        return this.allOrders.slice();
      }
  
    getOrdersAssigned() {
      return this.assignedOrders.slice();
    }
    getRecipesProfit() {
      return this.unassignedOrders.slice();
    }

  
  
  }