import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  onSaveData() {
    console.log("Pedidos Guardados")
  }

  onFetchData() {
    console.log("Pedidos Actualizados")
  }

}
