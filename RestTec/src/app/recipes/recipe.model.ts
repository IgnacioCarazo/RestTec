import { RecipeType } from '../recipe-type/recipe-type.model';
import { Ingredient } from '../shared/ingredient.model';

export class Recipe {
  public recipeName: string;
  public price: number;
  public calories: number;
  public prepareTime: number;
  public imagePath: string;
  public ingredients: Ingredient[];
  public finishTime: string;
  public type: RecipeType;

  constructor(recipeName: string, price: number, calories: number, prepareTime: number, imagePath: string, ingredients: Ingredient[],finishTime: string, recipeType: RecipeType) {
    this.recipeName = recipeName;
    this.price = price;
    this.prepareTime = 5;
    this.finishTime = "finishTime";
    this.calories = calories;
    this.imagePath = imagePath;
    this.ingredients = ingredients;
    this.type = recipeType;
  }
}
