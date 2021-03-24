import { Time } from "@angular/common";
import { Recipe } from "../recipes/recipe.model";
import { Chef } from "../shared/chef.model";

export class Order {
    id: number;
    assigned: boolean;
    chef: string;
    recipes: Recipe[];
    time: number;

    constructor(id: number, assigned: boolean, chef: string, recipes: Recipe[], time: number) {
    this.id = id;
    this.assigned = assigned;
    this.chef = chef;
    this.recipes = recipes;
    this.time = time;
    }
    
}