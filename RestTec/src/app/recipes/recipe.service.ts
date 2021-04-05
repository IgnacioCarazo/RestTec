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

  /**
  * @name setRecipes()
  * @argument {Recipe[]} recipes
  * @description  It set this service recipes with the value of the recipes argument.
  */
  setRecipes(recipes: Recipe[]) {
    this.recipes = recipes;
    this.recipesChanged.next(this.recipes.slice());
  }

  /**
  * @name setRecipe()
  * @argument {Recipe} recipe
  * @description  It sets the value of this service recipe with the recipe from the argument.
  */
  setRecipe(recipe: Recipe) {
    this.recipe = recipe;
  }

  /**
  * @name getRecipes()
  * @returns The array of recipes of this service.  
  */
  getRecipes() {
    return this.recipes.slice();
  }

  /**
  * @name getRecipe
  * @description It searches a recipe by its index
  * @returns {Recipe} A recipe
  */
  getRecipe(index: number) {
    return this.recipes[index];
  }

  /**
  * @name getActualRecipe()
  * @returns This service recipe  
  */
  getActualRecipe() {
    return this.recipe;
  }

 
  /**
  * @name addRecipe()
  * @argument {Recipe} recipe
  * @argument {string} recipeTypeName
  * @description  Adds a Recipe to this service array of recipes
  */
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

  /**
  * @name updateRecipe()
  * @argument {number} index
  * @argument {Recipe} newRecipe
  * @argument {string} recipeTypeName
  * @description  It updates the value of a recipe of this service recipes array. 
  */
  updateRecipe(index: number, newRecipe: Recipe, recipeTypeName: string) {
    const recipeTypeChecker = this.recipeTypeService.getRecipeTypeByName(recipeTypeName);
    if (recipeTypeChecker[1]) {
      newRecipe.type = <RecipeType>recipeTypeChecker[0];
    } else {
      newRecipe.type = new RecipeType("Sin especificar", "Sin especificar");
    }


    this.recipes[index] = newRecipe;
    this.recipesChanged.next(this.recipes.slice());
  }

  /**
  * @name deleteRecipe()
  * @argument {number} index
  * @description deletes a recipe by its index from this service recipes array.
  */
  deleteRecipe(index: number) {
    this.recipes.splice(index, 1);
    this.recipesChanged.next(this.recipes.slice());
  }
}
