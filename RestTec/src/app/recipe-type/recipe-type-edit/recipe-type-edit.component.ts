import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RecipeService } from 'src/app/recipes/recipe.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { RecipeTypeService } from '../recipe-type.service';

@Component({
  selector: 'app-recipe-type-edit',
  templateUrl: './recipe-type-edit.component.html',
  styleUrls: ['./recipe-type-edit.component.css']
})
export class RecipeTypeEditComponent implements OnInit {

  id: number;
  editMode = false;
  recipeForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private recipeTypeService: RecipeTypeService,
    private router: Router,
    private dataStorageService: DataStorageService
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.editMode = params['id'] != null;
      this.initForm();
    });
  }


  /**
    * @name onSubmit()
    * @description Whether it's on edit mode or on a new recipe mode this method will update or add a recipe with the 
    * value of the recipeForm.
    */
  onSubmit() {   
    if (this.editMode) {
      this.recipeTypeService.updateRecipeType(this.id, this.recipeForm.value);
      this.dataStorageService.storeRecipeTypes();
    } else {
      this.recipeTypeService.addRecipeType(this.recipeForm.value);
      this.dataStorageService.storeRecipeType(this.recipeForm.value);

    }
    this.onCancel();
  }


  /**
    * @name onCancel()
    * @description It sets the link to the previous one.
    */
  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  /**
    * @name initForm()
    * @description It sets the initial values of the recipe type in the form if it's on edit mode. If it's on
    * new recipe mode this values will be 'empty'.
    */
  private initForm() {
    let recipeTypeName = '';
    let recipeTypeDescription = '';

    if (this.editMode) {
      const recipeType = this.recipeTypeService.getRecipeType(this.id);
      recipeTypeName = recipeType.name;
      recipeTypeDescription = recipeType.description;
    }

    this.recipeForm = new FormGroup({
      name: new FormControl(recipeTypeName, Validators.required),
      description: new FormControl(recipeTypeDescription, Validators.required)
    });
  }
}



