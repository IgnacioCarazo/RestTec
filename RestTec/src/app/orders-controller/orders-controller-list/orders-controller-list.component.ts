import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Order } from 'src/app/orders/order.model';
import { OrdersService } from 'src/app/orders/orders.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';

@Component({
  selector: 'app-orders-controller-list',
  templateUrl: './orders-controller-list.component.html',
  styleUrls: ['./orders-controller-list.component.css']
})
export class OrdersControllerListComponent implements OnInit {
  orders: Order[];
  subscription: Subscription;

  constructor(private ordersService: OrdersService,
              private router: Router,
              private route: ActivatedRoute,
              private dataStorageService: DataStorageService) {
  }

  ngOnInit() {
    this.subscription = this.ordersService.assignedOrdersChanged
      .subscribe(
        (orders: Order[]) => {
          this.orders = orders;
        }
      );
    this.orders = this.ordersService.getAllAssignedOrders();
  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  /**
    * @name onFetchData()
    * @description In case the autofectching doesn't work it it fetches the data manually.
    */
  onFetchData() {
    this.dataStorageService.fetchOrders();
  }


}
