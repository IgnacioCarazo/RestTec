import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';

import { DataStorageService } from '../shared/data-storage.service';
import { RecipeType } from './recipe-type.model';
import { RecipeTypeService } from './recipe-type.service';

@Injectable({ providedIn: 'root' })
export class RecipeTypesResolverService implements Resolve<RecipeType[]> {
  constructor(
    private dataStorageService: DataStorageService,
    private recipeTypeService: RecipeTypeService
  ) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const recipeTypes = this.recipeTypeService.getRecipeTypes();

    if (recipeTypes.length === 0) {
      return this.dataStorageService.fetchRecipeTypes();
    } else {
      return recipeTypes;
    }
  }
}
