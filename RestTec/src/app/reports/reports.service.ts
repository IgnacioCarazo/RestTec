import { Subject } from "rxjs";
import { ClientReport } from "../shared/client-report.model";
import { RecipeReport } from "../shared/recipe-report.model";

export class ReportsService {
  recipesSoldChanged = new Subject<RecipeReport[]>();
  recipesProfitChanged = new Subject<RecipeReport[]>();
  recipesFeedbackChanged = new Subject<RecipeReport[]>();
  clientsChanged = new Subject<ClientReport[]>();


  private recipesSold: RecipeReport[] = [];
  private recipesProfit: RecipeReport[] = [];
  private recipesFeedback: RecipeReport[] = [];
  private clients: ClientReport[] = [];
  

  setRecipesSold(recipesSold: RecipeReport[]) {
    this.recipesSold = recipesSold;
    this.recipesSoldChanged.next(this.recipesSold.slice());

  }

  setRecipesProfit(recipesProfit: RecipeReport[]) {
    this.recipesProfit = recipesProfit;
    this.recipesProfitChanged.next(this.recipesProfit.slice());

  }

  setRecipesFeedBack(recipesFeedback: RecipeReport[]) {
    this.recipesFeedback = recipesFeedback;
    this.recipesFeedbackChanged.next(this.recipesFeedback.slice());

  }

  setClients(clients: ClientReport[]) {
    this.clients = clients;
    this.clientsChanged.next(this.clients.slice());

  }

  getRecipesSold() {
    return this.recipesSold.slice();
  }
  getRecipesProfit() {
    return this.recipesProfit.slice();
  }
  getRecipesFeedBack() {
    return this.recipesFeedback.slice();
  }

  getClients() {
    return this.clients.slice();
  }


}