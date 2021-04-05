import { Injectable, ɵɵsyntheticHostProperty } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';

import { Recipe } from '../recipes/recipe.model';
import { RecipeService } from '../recipes/recipe.service';

import { HeaderService } from '../header/header.service';
import { Observable } from 'rxjs';
import { RecipeType } from '../recipe-type/recipe-type.model';
import { RecipeTypeService } from '../recipe-type/recipe-type.service';
import { User } from './user.model';
import { Order } from '../orders/order.model';
import { OrdersService } from '../orders/orders.service';

@Injectable({ providedIn: 'root' })
export class DataStorageService {
  user: User;
  isAuthorized = false;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'
   })
  };
  constructor(private http: HttpClient, 
              private recipeService: RecipeService, 
              private headerService: HeaderService,
              private recipeTypeService: RecipeTypeService,
              private orderService: OrdersService) {}


  /**
  * http request del login
  */
  sendLoginInfo(email: string, password: string, isAdmin: boolean): Observable<User> {
    if (isAdmin) {
      return this.http.get<User>('https://localhost:5001/api/User/admin/'+ email + '/' + password);
    } else {
      return this.http.get<User>('https://localhost:5001/api/User/chef/'+ email + '/' + password);
    }
      
  }

/**
  * http requests de recetas
  */
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

  deleteRecipe(recipe: Recipe) {
    this.http.delete<Recipe>('https://localhost:5001/api/Recipe/delete/' + recipe.recipeName, this.httpOptions);
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
  /**
  * http requests de tipo de recetas
  */

  storeRecipeTypes() {
    const recipeTypes = this.recipeTypeService.getRecipeTypes();
    console.log(recipeTypes);
    this.http
      .put(
        'https://localhost:5001/api/RecipeType/updateType',
        recipeTypes
      )
      .subscribe(response => {
        console.log(response);
      });
      this.fetchRecipeTypes(); 
  }

  storeRecipeType(recipeType: RecipeType) {
    console.log(recipeType);
    this.http
      .post(
        'https://localhost:5001/api/RecipeType/newType',
        recipeType, this.httpOptions
      )
      .subscribe(response => {
        console.log(response);
      });
    this.fetchRecipes();
  }

  fetchRecipeTypes() {
      return this.http
      .get<RecipeType[]>(
        'https://localhost:5001/api/RecipeType'
      )
      .pipe(
        map(recipeTypes => {
          return recipeTypes.map(recipeType => {
            return {
              ...recipeType
            };
          });
        }),
        tap(recipeTypes => {
          console.log(recipeTypes);
          this.recipeTypeService.setRecipeTypes(recipeTypes);
        })
      )
  }

  /**
  * http requests de ordenes
  */
   fetchOrders() {
    return this.http
      .get<Order[]>(
        'https://localhost:5001/api/Order'
      )
      .pipe(
        map(orders => {
          return orders.map(order => {
            return {
              ...order
            };
          });
        }),
        tap(orders => {
          this.orderService.setAllOrders(orders);
        })
      )
  }

  assignOrder(chefName: string, orderID: number): Observable<Order> {
    console.log(chefName, orderID);
    return this.http.get<Order>('https://localhost:5001/api/Order/assign/'+ chefName + '/' + orderID);
  }

  deleteOrder(orderID: number) {
    this.http.delete<Recipe>('https://localhost:5001/api/Order/delete/' + orderID).subscribe();
    this.fetchOrders();
  }
  
}
