import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-orders-controller',
  templateUrl: './orders-controller.component.html',
  styleUrls: ['./orders-controller.component.css']
})
export class OrdersControllerComponent implements OnInit {

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
