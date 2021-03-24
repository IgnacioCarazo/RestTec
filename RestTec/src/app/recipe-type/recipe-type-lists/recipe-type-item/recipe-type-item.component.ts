import { Component, Input, OnInit } from '@angular/core';
import { RecipeType } from '../../recipe-type.model';

@Component({
  selector: 'app-recipe-type-item',
  templateUrl: './recipe-type-item.component.html',
  styleUrls: ['./recipe-type-item.component.css']
})
export class RecipeTypeItemComponent implements OnInit {
  @Input() recipeType: RecipeType;
  @Input() index: number;

  ngOnInit(): void {
  }

}
