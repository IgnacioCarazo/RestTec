import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { HeaderService } from 'src/app/header/header.service';
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
    this.order.chef = this.headerService.user.name;
    this.order.assigned = true;
    this.ordersService.updateOrder(this.id, this.order)
  }

  
}
