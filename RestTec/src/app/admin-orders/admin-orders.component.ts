import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Order } from '../orders/order.model';
import { OrdersService } from '../orders/orders.service';
import { DataStorageService } from '../shared/data-storage.service';

@Component({
  selector: 'app-admin-orders',
  templateUrl: './admin-orders.component.html',
  styleUrls: ['./admin-orders.component.css']
})
export class AdminOrdersComponent implements OnInit {
  orders: Order[];
  subscription: Subscription;

  constructor(private ordersService: OrdersService,
              private dataStorageService: DataStorageService) { }

  ngOnInit() {
    this.subscription = this.ordersService.ordersChanged
      .subscribe(
        (orders: Order[]) => {
          this.orders = orders;
        }
      );
    this.orders = this.ordersService.getAllOrders();
  }

  onFetchData() {
    this.dataStorageService.fetchOrders().subscribe();
  }

}
