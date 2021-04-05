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

  /**
  * @name onFetchData()
  * @description In case the autoloading of data doesn't work there's a button in the web page which calls this method
  * and will load the data.
  */
  onFetchData() {
    this.dataStorageService.fetchOrders().subscribe();
  }
  

}
