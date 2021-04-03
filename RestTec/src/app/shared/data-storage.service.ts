import { Injectable, ɵɵsyntheticHostProperty } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';

import { Recipe } from '../recipes/recipe.model';
import { RecipeService } from '../recipes/recipe.service';
import { User } from './user.model';
import { HeaderService } from '../header/header.service';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class DataStorageService {
  user: User;
  isAuthorized = false;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'
   })
  };
  constructor(private http: HttpClient, private recipeService: RecipeService, private headerService: HeaderService) {}


  sendLoginInfo(email: string, password: string, isAdmin: boolean): Observable<User> {
    if (isAdmin) {
      return this.http.get<User>('https://localhost:5001/api/User/admin/'+ email + '/' + password);
    } else {
      return this.http.get<User>('https://localhost:5001/api/User/chef/'+ email + '/' + password);
    }
      
  }


  storeRecipes() {
    const recipes = this.recipeService.getRecipes();
    this.http
      .put(
        'https://localhost:5001/api/Recipe/updateRecipe',
        recipes
      )
      .subscribe(response => {
        console.log(response);
      });
      this.fetchRecipes();
    
  }

  storeRecipe(recipe: Recipe) {
    console.log(recipe);
    this.http
      .post(
        'https://localhost:5001/api/Recipe/addRecipe',
        recipe, this.httpOptions
      )
      .subscribe(response => {
        console.log(response);
      });
    this.fetchRecipes();
  }

  fetchRecipes() {
    return this.http
      .get<Recipe[]>(
        'https://localhost:5001/api/Recipe'
      )
      .pipe(
        map(recipes => {
          return recipes.map(recipe => {
            return {
              ...recipe,
              ingredients: recipe.ingredients ? recipe.ingredients : []
            };
          });
        }),
        tap(recipes => {
          console.log(recipes);
          this.recipeService.setRecipes(recipes);
        })
      )
  }
}
