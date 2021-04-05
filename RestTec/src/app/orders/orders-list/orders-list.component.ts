import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Order } from '../order.model';
import { OrdersService } from '../orders.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent implements OnInit, OnDestroy {
  orders: Order[];
  subscription: Subscription;

  constructor(private ordersService: OrdersService,
              private router: Router,
              private route: ActivatedRoute,
              private dataStorageService: DataStorageService) {
  }

  ngOnInit() {
    this.subscription = this.ordersService.unAssignedOrdersChanged
      .subscribe(
        (orders: Order[]) => {
          this.orders = orders;
        }
      );
    this.orders = this.ordersService.getAllUnAssignedOrders();
  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  onSaveData() {
  }

  onFetchData() {
    this.dataStorageService.fetchOrders();
  }

}
