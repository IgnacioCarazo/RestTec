import { Component, OnInit } from '@angular/core';
import { Order } from '../orders/order.model';
import { OrdersService } from '../orders/orders.service';

@Component({
  selector: 'app-admin-orders',
  templateUrl: './admin-orders.component.html',
  styleUrls: ['./admin-orders.component.css']
})
export class AdminOrdersComponent implements OnInit {
  orders: Order[];

  constructor(private ordersService: OrdersService) { }

  ngOnInit(): void {
    this.orders = this.ordersService.getAllOrders();
  }

  onFetchData() {
    console.log("Se actualizaron los pedidos activos");
  }

}
