import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RecipeService } from 'src/app/recipes/recipe.service';
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
    private router: Router
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.editMode = params['id'] != null;
      this.initForm();
    });
  }


  onSubmit() {
    
    if (this.editMode) {
      this.recipeTypeService.updateRecipeType(this.id, this.recipeForm.value);
    } else {
      this.recipeTypeService.addRecipeType(this.recipeForm.value);
    }
    this.onCancel();
  }



  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

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



