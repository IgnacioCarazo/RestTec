import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Order } from 'src/app/orders/order.model';
import { OrdersService } from 'src/app/orders/orders.service';

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
              private router: Router) {
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


  onUpdateOrder() {
    this.order.chef = "Ignacio Carazo";
    this.ordersService.updateOrder(this.id, this.order)
  }

}
