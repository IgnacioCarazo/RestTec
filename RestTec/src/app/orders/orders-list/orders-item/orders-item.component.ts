import { Component, Input, OnInit } from '@angular/core';
import { Order } from '../../order.model';

@Component({
  selector: 'app-orders-item',
  templateUrl: './orders-item.component.html',
  styleUrls: ['./orders-item.component.css']
})
export class OrdersItemComponent implements OnInit {
  @Input() order: Order;
  @Input() index: number;
  
  constructor() { }

  ngOnInit(): void {
  }

}
