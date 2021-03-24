import { Component, Input, OnInit } from '@angular/core';
import { Order } from 'src/app/orders/order.model';

@Component({
  selector: 'app-orders-controller-item',
  templateUrl: './orders-controller-item.component.html',
  styleUrls: ['./orders-controller-item.component.css']
})
export class OrdersControllerItemComponent implements OnInit {

  @Input() order: Order;
  @Input() index: number;
  
  constructor() { }

  ngOnInit(): void {
  }

}
