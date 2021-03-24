import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { RecipeType } from '../recipe-type.model';
import { RecipeTypeService } from '../recipe-type.service';

@Component({
  selector: 'app-recipe-type-lists',
  templateUrl: './recipe-type-lists.component.html',
  styleUrls: ['./recipe-type-lists.component.css']
})
export class RecipeTypeListsComponent implements OnInit {
  recipeTypes: RecipeType[];
  subscription: Subscription;

  constructor(private recipeTypeService: RecipeTypeService,
              private router: Router,
              private route: ActivatedRoute,
              private dataStorageService: DataStorageService) {
  }

  ngOnInit() {
    this.subscription = this.recipeTypeService.recipeTypesChanged
      .subscribe(
        (recipeTypes: RecipeType[]) => {
          this.recipeTypes = recipeTypes;
        }
      );
    this.recipeTypes = this.recipeTypeService.getRecipeTypes();
  }

  onNewRecipeType() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onSaveData() {
    console.log("Tipos de Platos Guardados")
  }

  onFetchData() {
    console.log("Tipos de Platos Actualizados")
  }


}
