import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

import { Ingredient } from '../shared/ingredient.model';
import { RecipeType } from './recipe-type.model';

@Injectable()
export class RecipeTypeService {
  recipeTypesChanged = new Subject<RecipeType[]>();

  private recipeTypes: RecipeType[] = [];

  constructor() {}

  setRecipeTypes(recipes: RecipeType[]) {
    this.recipeTypes = recipes;
    this.recipeTypesChanged.next(this.recipeTypes.slice());
  }

  getRecipeTypes() {
    return this.recipeTypes.slice();
  }

  getRecipeType(index: number) {
    return this.recipeTypes[index];
  }


  addRecipeType(recipeType: RecipeType) {
    console.log(recipeType);
    this.recipeTypes.push(recipeType);
    this.recipeTypesChanged.next(this.recipeTypes.slice());
  }

  updateRecipeType(index: number, newRecipeType: RecipeType) {
    this.recipeTypes[index] = newRecipeType;
    this.recipeTypesChanged.next(this.recipeTypes.slice());
  }

  deleteRecipeType(index: number) {
    this.recipeTypes.splice(index, 1);
    this.recipeTypesChanged.next(this.recipeTypes.slice());
  }
}
