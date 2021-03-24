
export class ReportsService {
  private recipesSold = ["Pizza", "Pasta", "Gallo Pinto"];
  private recipesProfit = ["Gallo Pinto"];
  private recipesFeedback = ["Pizza", "Ramen"];
  private clients = ["Haziel", "Nacho", "Joseph"];
  

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