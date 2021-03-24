import { Component, OnInit } from '@angular/core';
import { DataStorageService } from '../shared/data-storage.service';
import { ReportsService } from './reports.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  recipesSold = [];
  recipesProfit = [];
  recipesFeedback = [];
  clients = [];
  constructor(private reportsService: ReportsService,
              private dataStorageService: DataStorageService) { }

  ngOnInit() {
    this.recipesSold = this.reportsService.getRecipesSold();
    this.recipesProfit = this.reportsService.getRecipesProfit();
    this.recipesFeedback = this.reportsService.getRecipesFeedBack();
    this.clients = this.reportsService.getClients();
  }

  onSaveData() {
    console.log("Reportes Guardados");
  }

  onFetchData() {
    console.log("Reportes Actualizados");
  }


}
