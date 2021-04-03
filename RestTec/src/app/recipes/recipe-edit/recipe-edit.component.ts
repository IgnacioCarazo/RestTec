import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators, FormBuilder } from '@angular/forms';

import { RecipeService } from '../recipe.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { RecipeTypeService } from 'src/app/recipe-type/recipe-type.service';
import { RecipeType } from 'src/app/recipe-type/recipe-type.model';
import { Recipe } from '../recipe.model';

@Component({
  selector: 'app-recipe-edit',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {
  id: number;
  editMode = false;
  recipeForm: FormGroup;
  recipeTypeForm: FormGroup;
  recipeTypes = [];

  constructor(
    private route: ActivatedRoute,
    private recipeService: RecipeService,
    private router: Router,
    private dataStorageService: DataStorageService,
    private formBuilder: FormBuilder,
    private recipeTypeService: RecipeTypeService
  ) {
    this.recipeTypeForm = this.formBuilder.group({
      recipeTypes: ['']
    });

    this.recipeTypes = this.recipeTypeService.getRecipeTypes();
    console.log(this.recipeTypes);
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.editMode = params['id'] != null;
      this.initForm();
    });
  }

  get controls() {
    return (<FormArray>this.recipeForm.get('ingredients')).controls;
  }

  onSubmit() {
    if (this.editMode) {
      this.recipeService.updateRecipe(this.id, this.recipeForm.value);
      this.recipeService.setRecipe(this.recipeForm.value);
    } else {
      this.recipeService.addRecipe(this.recipeForm.value);
      this.recipeService.setRecipe(this.recipeForm.value);

    }
    console.log(this.recipeService.recipe);
    this.dataStorageService.storeRecipe(this.recipeService.recipe);
    this.onCancel();
  }

  onAddIngredient() {
    (<FormArray>this.recipeForm.get('ingredients')).push(
      new FormGroup({
        name: new FormControl(null, Validators.required),
        amount: new FormControl(null, [
          Validators.required,
          Validators.pattern(/^[1-9]+[0-9]*$/)
        ])
      })
    );
  }

  onDeleteIngredient(index: number) {
    (<FormArray>this.recipeForm.get('ingredients')).removeAt(index);
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  private initForm() {
    let recipeName = '';
    let recipeImagePath = '';
    let recipePrice = 0;
    let recipeCalories = 0;
    let recipePrepareTime = 0;
    let recipeFinishTime = '';
    let recipeType: RecipeType;
    let recipeTypeName = '';
    let recipeIngredients = new FormArray([]);

    if (this.editMode) {
      const recipe = this.recipeService.getRecipe(this.id);
      recipeName = recipe.recipeName;
      recipeImagePath = recipe.imagePath;
      recipePrice = recipe.price;
      recipeCalories = recipe.calories;
      recipePrepareTime = recipe.prepareTime;
      recipeFinishTime = recipe.finishTime;
      recipeType = recipe.type;
      recipeTypeName = recipe.type.name;
      if (recipe['ingredients']) {
        for (let ingredient of recipe.ingredients) {
          recipeIngredients.push(
            new FormGroup({
              name: new FormControl(ingredient.name, Validators.required),
              amount: new FormControl(ingredient.amount, [
                Validators.required,
                Validators.pattern(/^[1-9]+[0-9]*$/)
              ])
            })
          );
        }
      }
    }

    this.recipeForm = new FormGroup({
      recipeName: new FormControl(recipeName, Validators.required),
      prepareTime: new FormControl(recipePrepareTime),
      finishTime: new FormControl(recipeFinishTime),
      imagePath: new FormControl(recipeImagePath, Validators.required),
      price: new FormControl(recipePrice, Validators.required),
      calories: new FormControl(recipeCalories, Validators.required),

      ingredients: recipeIngredients
    });
  }
}
