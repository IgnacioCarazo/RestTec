import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';

import { Recipe } from '../recipes/recipe.model';
import { RecipeService } from '../recipes/recipe.service';

@Injectable({ providedIn: 'root' })
export class DataStorageService {
  isAuthorized = false;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient, private recipeService: RecipeService) {}


  sendLoginInfo(email: string, password: string) {
    console.log(email);
    console.log(password);
    this.http.get('https://localhost:5001/api/User/chef/'+ email + '/' + password).
    subscribe( responseLogin => {
      console.log(responseLogin);
    });
    
  }

  storeRecipes() {
    const recipes = this.recipeService.getRecipes();
    this.http
      .put(
        'https://ng-course-recipe-book-72d1b-default-rtdb.firebaseio.com/recipes.json',
        recipes
      )
      .subscribe(response => {
        console.log(response);
      });
  }

  storeRecipe() {
    const recipe = this.recipeService.getActualRecipe();
    this.http
      .post(
        'https://localhost:5001/api/Recipe/addRecipe',
        recipe, this.httpOptions
      )
      .subscribe(response => {
        console.log(response);
      });
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
