import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { HeaderService } from 'src/app/header/header.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Order } from '../order.model';
import { OrdersService } from '../orders.service';

@Component({
  selector: 'app-orders-detail',
  templateUrl: './orders-detail.component.html',
  styleUrls: ['./orders-detail.component.css']
})
export class OrdersDetailComponent implements OnInit {
  order: Order;
  id: number;

  constructor(private ordersService: OrdersService,
              private headerService: HeaderService,
              private route: ActivatedRoute,
              private router: Router,
              private dataStorageService: DataStorageService) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.order = this.ordersService.getOrderFromAll(this.id);
        }
      );
  }


  onUpdateOrder() {
    this.dataStorageService.assignOrder(this.headerService.user.name, this.order.orderID);
    this.dataStorageService.fetchOrders();
  }

  
}
