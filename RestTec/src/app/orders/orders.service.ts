import { Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { RecipeType } from "../recipe-type/recipe-type.model";
import { Recipe } from "../recipes/recipe.model";
import { Ingredient } from "../shared/ingredient.model";
import { Order } from "./order.model";

@Injectable()
export class OrdersService {
  ordersChanged = new Subject<Order[]>();


    private allOrders = [
        new Order(135, true, "Ignacio Carazo", [
            new Recipe("Pizza", 5000, 500,5, "",[new Ingredient("Pepperoni",3)],"", new RecipeType("Almuerzo",""))], 10),
        new Order(120, false, "", [
          new Recipe("Gallo Pinto", 5000, 500,5, "",[new Ingredient("Pepperoni",3)],"", new RecipeType("Desayuno",""))], 5),
        new Order(137, true, "Joseph Jimenez", [
          new Recipe("Ramen", 5000, 500,5, "",[new Ingredient("Pepperoni",3)],"", new RecipeType("Cena",""))], 7),
          
    ];
    private assignedOrders = [
      new Order(137, true, "Ignacio Carazo", [
        new Recipe("Ramen", 5000, 500,5, "",[new Ingredient("Pepperoni",3)], "",new RecipeType("Cena",""))], 5),
      new Order(135, true, "Ignacio Carazo", [
        new Recipe("Pizza", 5000, 500,5, "",[new Ingredient("Pepperoni",3)], "",new RecipeType("Almuerzo",""))], 5)
    ];

    getOrderFromAll(index: number) {
      return this.allOrders[index];
    }

    getOrderFromAssigned(index: number) {
      return this.assignedOrders[index];
    }

    getAllOrders() {
        return this.allOrders.slice();
      }

    updateOrder(index: number, newOrder: Order) {
      this.allOrders[index] = newOrder;
      this.ordersChanged.next(this.allOrders.slice());
    }
  
    getOrdersAssigned() {
      return this.assignedOrders.slice();
    }
   

    deleteOrder(index: number) {
      this.allOrders.splice(index, 1);
      this.ordersChanged.next(this.allOrders.slice());
    }
  
  }