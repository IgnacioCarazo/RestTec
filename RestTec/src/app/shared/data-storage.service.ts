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
import { RecipeReport } from './recipe-report.model';
import { ReportsService } from '../reports/reports.service';
import { ClientReport } from './client-report.model';

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
              private orderService: OrdersService,
              private reportsService: ReportsService) {}


  /**
   * ------------------------------------------------
  * http request del login
  * ------------------------------------------------
  */

  /**
  * @name sendLoginInfo()
  * @argument {string} email
  * @argument {string} password
  * @argument {boolean} isAdmin
  * @description  It sends an http get request to the backend wiht the info of the user's email and password. The
  * link varies dependin of the value of isAdmin.
  * @returns {Observable<User>} A user observable.
  */
  sendLoginInfo(email: string, password: string, isAdmin: boolean): Observable<User> {
    if (isAdmin) {
      return this.http.get<User>('https://localhost:5001/api/User/admin/'+ email + '/' + password);
    } else {
      return this.http.get<User>('https://localhost:5001/api/User/chef/'+ email + '/' + password);
    }
      
  }

/**
 * ------------------------------------------------
  * http requests de recetas
  * ------------------------------------------------
  */

  /**
  * @name storeRecipes()
  * @description It sends an http put request to the backend to store all the recipes.
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

  /**
  * @name deleteRecipe()
  * @argument {Recipe} recipe
  * @description Deletes a recipe from the backend by sending an http delete request with the recipe name.
  */
  deleteRecipe(recipe: Recipe) {
    this.http.delete<Recipe>('https://localhost:5001/api/Recipe/delete/' + recipe.recipeName, this.httpOptions);
  }

  /**
  * @name storeRecipe()
  * @argument {Recipe} recipe
  * @description It sends an http post request with a recipe as argument to store the respective recipe 
  * in the database.
  */
  storeRecipe(recipe: Recipe) {
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

  /**
  * @name fetchRecipes()
  * @returns An observable array of recipes  
  */
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
          this.recipeService.setRecipes(recipes);
        })
      )
  }
  /**
   * ------------------------------------------------
  * http requests de tipo de recetas
  * ------------------------------------------------
  */

  /**
  * @name storeRecipeTypes()
  * @description Sends an http put request to store the recipe types array in the database
  */
  storeRecipeTypes() {
    const recipeTypes = this.recipeTypeService.getRecipeTypes();
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

  /**
  * @name storeRecipeType()
  * @argument {RecipeType} recipeType
  * @description  Sends an http post request to the database to store the recipeType.
  */
  storeRecipeType(recipeType: RecipeType) {
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

  /**
  * @name fetchRecipeTypes()
  * @description  Sends an http get request to the backend to fetch the array of recipe types.
  * @returns An observable of an array of recipe types.
  */
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
   * ------------------------------------------------
  * http requests de ordenes
  * ------------------------------------------------
  */

   /**
  * @name fetchOrders()
  * @description Sends an http get request to fetch the orders array from the database.
  * @returns An observable of an array of orders.
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

  /**
  * @name assignOrder()
  * @description  Sends an http request to the backend to assign an order to a chef.
  */
  assignOrder(chefName: string, orderID: number): Observable<Order> {
    console.log(chefName, orderID);
    return this.http.get<Order>('https://localhost:5001/api/Order/assign/'+ chefName + '/' + orderID);
  }

  /**
  * @name deleteOrder()
  * @argument {number} orderId
  * @description  Sends an http delete request to the backend to delete an order.
  */
  deleteOrder(orderID: number) {
    this.http.delete('https://localhost:5001/api/Order/delete/' + orderID).subscribe(
      response => {
        console.log(response);
      }
    );
    this.fetchOrders();
  }


  /**
   * -----------------------------------------------
  * http requests de reportes
  * ------------------------------------------------
  */

  /**
  * @name fetchRecipeSold()
  * @description Sends an http get request to fetch the orders array from the database.
  * @returns An observable of an array of orders.
  */
   fetchRecipeSold() {
    return this.http
      .get<RecipeReport[]>(
        'https://localhost:5001/api/Order'
      )
      .pipe(
        map(recipesSold => {
          return recipesSold.map(recipeSold => {
            return {
              ...recipeSold
            };
          });
        }),
        tap(recipesSold => {
          this.reportsService.setRecipesSold(recipesSold);
        })
      )
  }
  /**
  * @name fetchRecipeProfit()
  * @description Sends an http get request to fetch the orders array from the database.
  * @returns An observable of an array of orders.
  */
   fetchRecipeProfit() {
    return this.http
      .get<RecipeReport[]>(
        'https://localhost:5001/api/Order'
      )
      .pipe(
        map(recipesProfit => {
          return recipesProfit.map(recipeProfit => {
            return {
              ...recipeProfit
            };
          });
        }),
        tap(recipesProfit => {
          this.reportsService.setRecipesProfit(recipesProfit);
        })
      )
  }
  /**
  * @name fetchOrders()
  * @description Sends an http get request to fetch the orders array from the database.
  * @returns An observable of an array of orders.
  */
   fetchRecipeFeedback() {
    return this.http
      .get<RecipeReport[]>(
        'https://localhost:5001/api/Order'
      )
      .pipe(
        map(recipesFeedback => {
          return recipesFeedback.map(recipeFeedback => {
            return {
              ...recipeFeedback
            };
          });
        }),
        tap(recipesFeedback => {
          this.reportsService.setRecipesFeedBack(recipesFeedback);
        })
      )
  }
  /**
  * @name fetchOrders()
  * @description Sends an http get request to fetch the orders array from the database.
  * @returns An observable of an array of orders.
  */
   fetchClient() {
    return this.http
      .get<ClientReport[]>(
        'https://localhost:5001/api/Order'
      )
      .pipe(
        map(clients => {
          return clients.map(client => {
            return {
              ...client
            };
          });
        }),
        tap(clients => {
          this.reportsService.setClients(clients);
        })
      )
  }
  
}
