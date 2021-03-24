import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
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
              private route: ActivatedRoute,
              private router: Router) {
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
    this.order.chef = "Ignacio Carazo";
    this.order.assigned = true;
    this.ordersService.updateOrder(this.id, this.order)
  }

  
}
