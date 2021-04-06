import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
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
    this.subscription = this.reportsService.recipesSoldChanged
      .subscribe(
        (recipesSold: RecipeReport[]) => {
          this.recipesSold = recipesSold;
        }
      );
    this.recipesSold = this.reportsService.getRecipesSold();
  }
}
