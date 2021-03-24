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
    this.subscription = this.ordersService.ordersChanged
      .subscribe(
        (orders: Order[]) => {
          this.orders = orders;
        }
      );
    this.orders = this.ordersService.getOrdersAssigned();
  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  onSaveData() {
    console.log("Save");
  }

  onFetchData() {
    console.log("Fetch");
  }


}
