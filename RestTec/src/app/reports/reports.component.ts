import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ClientReport } from '../shared/client-report.model';
import { DataStorageService } from '../shared/data-storage.service';
import { RecipeReport } from '../shared/recipe-report.model';
import { ReportsService } from './reports.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  subscription: Subscription;
  recipesSold = [];
  recipesProfit = [];
  recipesFeedback = [];
  clients = [];
  constructor(private reportsService: ReportsService,
              private dataStorageService: DataStorageService) { }

  ngOnInit() {
    //this.recipesSold = this.reportsService.getRecipesSold();
    //this.recipesProfit = this.reportsService.getRecipesProfit();
    //this.recipesFeedback = this.reportsService.getRecipesFeedBack();
    //this.clients = this.reportsService.getClients();

    // Subscription para recipes sold
    this.subscription = this.reportsService.recipesSoldChanged
      .subscribe(
        (recipesSold: RecipeReport[]) => {
          this.recipesSold = recipesSold;
        }
      );
    this.recipesSold = this.reportsService.getRecipesSold();

    // Subscription para recipes profit
    this.subscription = this.reportsService.recipesProfitChanged
      .subscribe(
        (recipesProfit: RecipeReport[]) => {
          this.recipesProfit = recipesProfit;
        }
      );
    this.recipesProfit = this.reportsService.getRecipesProfit();

    // Subscription para recipes feedback
    this.subscription = this.reportsService.recipesFeedbackChanged
      .subscribe(
        (recipesFeedback: RecipeReport[]) => {
          this.recipesFeedback = recipesFeedback;
        }
      );
    this.recipesFeedback = this.reportsService.getRecipesFeedBack();

    // Subscription para clients
    this.subscription = this.reportsService.clientsChanged
      .subscribe(
        (clients: ClientReport[]) => {
          this.clients = clients;
        }
      );
    this.clients = this.reportsService.getClients();
  }

  onFetchData() {
    this.dataStorageService.fetchRecipesFeedback().subscribe();
    this.dataStorageService.fetchRecipesProfit().subscribe();
    this.dataStorageService.fetchRecipesSold().subscribe();
    this.dataStorageService.fetchClients().subscribe();
  }
}
