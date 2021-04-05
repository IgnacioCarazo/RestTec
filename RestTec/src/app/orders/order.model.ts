import { Time } from "@angular/common";
import { Recipe } from "../recipes/recipe.model";
import { User } from "../shared/user.model";

export class Order {
    date: string;
    userID: number;
    orderID: number;
    assigned: boolean;
    chefName: string;
    recipeIncluded: Recipe[];
    orderTime: string;
    totalAmount: number;


    constructor(userID: number, assigned: boolean, chefName: string, recipeIncluded: Recipe[], orderTime: string) {
    this.userID = userID;
    this.assigned = assigned;
    this.chefName = chefName;
    this.recipeIncluded = recipeIncluded;
    this.orderTime = orderTime;
    }
    
}