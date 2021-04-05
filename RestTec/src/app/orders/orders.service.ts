import { Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { HeaderService } from "../header/header.service";
import { RecipeType } from "../recipe-type/recipe-type.model";
import { Recipe } from "../recipes/recipe.model";
import { Ingredient } from "../shared/ingredient.model";
import { User } from "../shared/user.model";
import { Order } from "./order.model";

@Injectable()
export class OrdersService {
  ordersChanged = new Subject<Order[]>();
  assignedOrdersChanged = new Subject<Order[]>();
  unAssignedOrdersChanged = new Subject<Order[]>();


    private allOrders : Order[] = [];
    private assignedOrders : Order[] = [];
    private unAssignedOrders : Order[] = [];

    constructor(private headerService: HeaderService) {}

    /**
    * @name getOrderFromAll()
    * @argument {number} index  -A number indicating an index
    * @description Gets an order by its index from the array of al the orders 
    */
    getOrderFromAll(index: number) {
      return this.allOrders[index];
    }

    /**
    * @name getOrderFromAssigned()
    * @argument {number} index -A number indicating an index
    * @description Gets an order by its index from the array of the assigned orders.
    */
    getOrderFromAssigned(index: number) {
      return this.assignedOrders[index];
    }

    /**
    * @name setAllOrders()
    * @argument {Order[]} orders -An array which contains every order
    * @description It sets the value of the array of allOrders from this service and calls two other methods
    * which will set the values of assignedOrders and unAssignedOrders.
    */
    setAllOrders(orders: Order[]) {
      const user: User = this.headerService.user;
      this.allOrders = orders;
      this.ordersChanged.next(this.allOrders.slice());
      this.setAssignedOrders(user.name)
      this.setUnAssignedOrders(user.name);
    }

    /**
    * @name setUnAssignedOrders()
    * @argument {string} username -The name of the user that's connected at the moment
    * @description It compares the username value with each of the chef's names from every order, and if
    * they are differente it adds the respective order to the unassigned orders array.
    */
    setUnAssignedOrders(username: string) {
      const unAssignedOrders: Order[] = [];
      for (var order of this.allOrders) {
        if (username != order.chefName) {
          unAssignedOrders.push(order);
        } 
      }
      this.unAssignedOrders = unAssignedOrders;
      this.unAssignedOrdersChanged.next(this.unAssignedOrders.slice());

    }

    /**
    * @name setAssignedOrders()
    * @argument {string} username -The name of the user that's connected at the moment
    * @description It compares the username value with each of the chef's names from every order, and if they
    * are the same it adds the respective order to the assigned orders array.
    */
    setAssignedOrders(username: string) {
      const assignedOrders: Order[] = [];
      for (var order of this.allOrders) {
        if (username === order.chefName) {
          assignedOrders.push(order);
        } 
      }
      this.assignedOrders = assignedOrders;
      this.assignedOrdersChanged.next(this.assignedOrders.slice());
    }
    
    /**
    * @name getAllOrders()
    * @returns {Order[]} An array of all orders
    */
    getAllOrders() {
        return this.allOrders.slice();
    }

    /**
    * @name getAllAssignedOrders()
    * @returns {Order[]} An array of all assigned orders
    */
    getAllAssignedOrders() {
        return this.assignedOrders.slice();
    }
    /**
    * @name getAllUnAssignedOrders() 
    * @returns {Order[]} An array of all unassigned orders
    */
    getAllUnAssignedOrders() {
      return this.unAssignedOrders.slice();
    }

    /**
    * @name deleteOrder()
    * @argument {number} index
    * @description It deletes an order from the all orders array
    */
    deleteOrder(index: number) {
      this.allOrders.splice(index, 1);
      this.ordersChanged.next(this.allOrders.slice());
    }

    /**
    * @name deleteOrderFromUnAssigned()
    * @argument {number} index
    * @description It deletes an order from the unassigned orders
    */
    deleteOrderFromUnAssigned(index: number) {
      this.assignedOrders.splice(index, 1);
      this.assignedOrdersChanged.next(this.assignedOrders.slice());
    }

    /**
    * @name  completeOrderAssigned()
    * @argument {number} index
    * @description It deletes an order from the assigned orders array
    */
    completeOrderAssigned(index: number) {
      this.assignedOrders.splice(index, 1);
      this.assignedOrdersChanged.next(this.assignedOrders.slice());
    }
  
  }