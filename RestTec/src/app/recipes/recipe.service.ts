import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

import { Recipe } from './recipe.model';
import { Ingredient } from '../shared/ingredient.model';
import { RecipeType } from '../recipe-type/recipe-type.model';
import { RecipeTypeService } from '../recipe-type/recipe-type.service';

@Injectable()
export class RecipeService {
  recipesChanged = new Subject<Recipe[]>();
  recipe: Recipe;
  private recipes: Recipe[] = [];

  constructor(private recipeTypeService: RecipeTypeService) {}

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

 

  addRecipe(recipe: Recipe, recipeTypeName: string) {
    const recipeTypeChecker = this.recipeTypeService.getRecipeTypeByName(recipeTypeName);
  

    if (recipeTypeChecker[1]) {
      recipe.type = <RecipeType>recipeTypeChecker[0];
    } else {
      recipe.type = new RecipeType("Sin especificar", "Sin especificar");
    }

    this.recipes.push(recipe);
    this.recipesChanged.next(this.recipes.slice());
  }

  updateRecipe(index: number, newRecipe: Recipe, recipeTypeName: string) {
    console.log(recipeTypeName);
    const recipeTypeChecker = this.recipeTypeService.getRecipeTypeByName(recipeTypeName);
    console.log(recipeTypeChecker[0]);
    console.log(recipeTypeChecker[1]);


    if (recipeTypeChecker[1]) {
      newRecipe.type = <RecipeType>recipeTypeChecker[0];
    } else {
      newRecipe.type = new RecipeType("Sin especificar", "Sin especificar");
    }


    this.recipes[index] = newRecipe;
    this.recipesChanged.next(this.recipes.slice());
  }

  deleteRecipe(index: number) {
    this.recipes.splice(index, 1);
    this.recipesChanged.next(this.recipes.slice());
  }
}
