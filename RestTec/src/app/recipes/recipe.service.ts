import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

import { Recipe } from './recipe.model';
import { Ingredient } from '../shared/ingredient.model';
import { RecipeType } from '../recipe-type/recipe-type.model';

@Injectable()
export class RecipeService {
  recipesChanged = new Subject<Recipe[]>();
  recipe: Recipe;
  private recipes: Recipe[] = [];

  constructor() {}

  setRecipes(recipes: Recipe[]) {
    this.recipes = recipes;
    this.recipesChanged.next(this.recipes.slice());
  }

  setRecipe(recipe: Recipe) {
    this.recipe = recipe;
  }

  getRecipes() {
    return this.recipes.slice();
  }

  getRecipe(index: number) {
    return this.recipes[index];
  }

  getActualRecipe() {
    return this.recipe;
  }

 

  addRecipe(recipe: Recipe) {
    recipe.type = new RecipeType("Almuerzo","Se come al medio dia");
    this.recipes.push(recipe);
    this.recipesChanged.next(this.recipes.slice());
  }

  updateRecipe(index: number, newRecipe: Recipe) {
    this.recipes[index] = newRecipe;
    this.recipesChanged.next(this.recipes.slice());
  }

  deleteRecipe(index: number) {
    this.recipes.splice(index, 1);
    this.recipesChanged.next(this.recipes.slice());
  }
}
