import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RecipeType } from '../recipe-type.model';
import { RecipeTypeService } from '../recipe-type.service';

@Component({
  selector: 'app-recipe-type-detail',
  templateUrl: './recipe-type-detail.component.html',
  styleUrls: ['./recipe-type-detail.component.css']
})
export class RecipeTypeDetailComponent implements OnInit {
  recipeType: RecipeType;
  id: number;

  constructor(private recipeTypeService: RecipeTypeService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.recipeType = this.recipeTypeService.getRecipeType(this.id);
        }
      );
  }

  

  onEditRecipe() {
    this.router.navigate(['edit'], {relativeTo: this.route});
    
  }

  onDeleteRecipe() {
    this.recipeTypeService.deleteRecipeType(this.id);
    this.router.navigate(['/recipe-type']);
  }

}
