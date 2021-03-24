import { RecipeType } from '../recipe-type/recipe-type.model';
import { Ingredient } from '../shared/ingredient.model';

export class Recipe {
  public name: string;
  public price: number;
  public calories: number;
  public imagePath: string;
  public ingredients: Ingredient[];
  public type: RecipeType;

  constructor(name: string, price: number, calories: number, imagePath: string, ingredients: Ingredient[], recipeType: RecipeType) {
    this.name = name;
    this.price = price;
    this.calories = calories;
    this.imagePath = imagePath;
    this.ingredients = ingredients;
    this.type = recipeType;
  }
}
