import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { HeaderService } from 'src/app/header/header.service';
import { Order } from 'src/app/orders/order.model';
import { OrdersService } from 'src/app/orders/orders.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';

@Component({
  selector: 'app-orders-controller-detail',
  templateUrl: './orders-controller-detail.component.html',
  styleUrls: ['./orders-controller-detail.component.css']
})
export class OrdersControllerDetailComponent implements OnInit {

  order: Order;
  id: number;

  constructor(private ordersService: OrdersService,
              private route: ActivatedRoute,
              private router: Router,
              private headerService: HeaderService,
              private dataStorageService: DataStorageService) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.order = this.ordersService.getOrderFromAssigned(this.id);
        }
      );
  }


  /**
    * @name onCompleteOrder
    * @description It lets the backend know that an order has been completed and deletes it from the assigned orders.
    */
  onCompleteOrder() {
    this.dataStorageService.deleteOrder(this.order.orderID);
    this.router.navigate(['/orders-controller']);
    this.ordersService.completeOrderAssigned(this.id);
  }

}
