import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

import { Ingredient } from '../shared/ingredient.model';
import { RecipeType } from './recipe-type.model';

@Injectable()
export class RecipeTypeService {
  recipeTypesChanged = new Subject<RecipeType[]>();

  private recipeTypes: RecipeType[];

  constructor() {
    this.recipeTypes = []
  }

  /**
  * @name setRecipeTypes()
  * @argument {RecipeType[]} recipes
  * @description Sets the value of the recipe types of this service.
  */
  setRecipeTypes(recipes: RecipeType[]) {
    this.recipeTypes = recipes;
    this.recipeTypesChanged.next(this.recipeTypes.slice());
  }

  /**
  * @name getRecipeTypes()
  * @returns {recipeType[]} An array of recipe types/
  */
  getRecipeTypes() {
    return this.recipeTypes.slice();
  }

  /**
  * @name getRecipeTypeByName()
  * @argument {string} name
  * @description It searches within the recipe types array of this service if there's a recipe type with that name
  * @returns An array on which its first position is an object RecipeType and its second position a boolean which 
  * indicates if there was a recipe type with that name
  */
  getRecipeTypeByName(name: string) {
    let recipeTypeChecker = [];
    for (let recipeType of this.recipeTypes) {
      console.log(recipeType.name);
        if (recipeType.name === name) {
          recipeTypeChecker = [recipeType, true];  
          return recipeTypeChecker; 
        } 
    }
    if (recipeTypeChecker.length === 0) {
      return [new RecipeType('',''), false];
    }
  }

  /**
  * @name getRecipeType()
  * @argument {number} index
  * @description It searches and returns a recipe type within the recipe types array. 
  * @returns {RecipeType} A recipe type.  
  */
  getRecipeType(index: number) {
    return this.recipeTypes[index];
  }


  /**
  * @name addRecipeType()
  * @argument {RecipeType} recipeType
  * @description Adds a recipe type to the array of recipe types of this service.
  */
  addRecipeType(recipeType: RecipeType) {
    this.recipeTypes.push(recipeType);
    this.recipeTypesChanged.next(this.recipeTypes.slice());
  }

  /**
  * @name updateRecipeType()
  * @argument {number} index
  * @argument {RecipeType} newRecipeType
  * @description  Updates the value of a recipe type within the recipe types array of this service.
  */
  updateRecipeType(index: number, newRecipeType: RecipeType) {
    this.recipeTypes[index] = newRecipeType;
    this.recipeTypesChanged.next(this.recipeTypes.slice());
  }

  /**
  * @name deleteRecipeType()
  * @argument {number} indec
  * @description Deletes a recipe type within the recipe types array of this service.
  */
  deleteRecipeType(index: number) {
    this.recipeTypes.splice(index, 1);
    this.recipeTypesChanged.next(this.recipeTypes.slice());
  }

  
}
