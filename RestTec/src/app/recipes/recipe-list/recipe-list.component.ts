import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataStorageService } from 'src/app/shared/data-storage.service';

import { Recipe } from '../recipe.model';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit, OnDestroy {
  recipes: Recipe[];
  subscription: Subscription;

  constructor(private recipeService: RecipeService,
              private router: Router,
              private route: ActivatedRoute,
              private dataStorageService: DataStorageService) {
  }

  ngOnInit() {
    this.subscription = this.recipeService.recipesChanged
      .subscribe(
        (recipes: Recipe[]) => {
          this.recipes = recipes;
        }
      );
    this.recipes = this.recipeService.getRecipes();
  }

  /**
  * @name onNewRecipe
  * @description Sets the link to 'new'
  */
  onNewRecipe() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  /**
  * @name onSaveData()
  * @description Saves the data manually in case it doesn't work the autosave.
  */
  onSaveData() {
    this.dataStorageService.storeRecipes();
  }

  /**
  * @name onFetchData()
  * @description Fetches data manually in case the autofetch doesn't work
  */
  onFetchData() {
    this.dataStorageService.fetchRecipes().subscribe();
  }


}
