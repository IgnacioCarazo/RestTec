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

    getOrderFromAll(index: number) {
      return this.allOrders[index];
    }

    getOrderFromAssigned(index: number) {
      return this.assignedOrders[index];
    }

    setAllOrders(orders: Order[]) {
      const user: User = this.headerService.user;
      this.allOrders = orders;
      this.ordersChanged.next(this.allOrders.slice());

      this.setAssignedOrders(user.name)
      this.setUnAssignedOrders(user.name);
    }

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

    getAllOrders() {
        return this.allOrders.slice();
    }

    getAllAssignedOrders() {
        return this.assignedOrders.slice();
    }

      getAllUnAssignedOrders() {
        return this.unAssignedOrders.slice();
    }

    updateOrder(index: number, newOrder: Order) {
      this.allOrders[index] = newOrder;
      this.unAssignedOrders.splice(index, 1);
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