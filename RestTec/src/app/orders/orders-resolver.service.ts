import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { Order } from '../orders/order.model';
import { OrdersService } from '../orders/orders.service';

import { DataStorageService } from '../shared/data-storage.service';


@Injectable({ providedIn: 'root' })
export class OrdersResolver implements Resolve<Order[]> {
  constructor(
    private dataStorageService: DataStorageService,
    private orderService: OrdersService
  ) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const orders = this.orderService.getAllAssignedOrders();
    console.log(orders);
    if (orders.length === 0) {
      return this.dataStorageService.fetchOrders();
    } else {
      return orders;
    }
  }
}