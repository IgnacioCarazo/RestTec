import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { RecipesComponent } from './recipes/recipes.component';
import { RecipeListComponent } from './recipes/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './recipes/recipe-detail/recipe-detail.component';
import { RecipeItemComponent } from './recipes/recipe-list/recipe-item/recipe-item.component';
import { DropdownDirective } from './shared/dropdown.directive';
import { AppRoutingModule } from './app-routing.module';
import { RecipeStartComponent } from './recipes/recipe-start/recipe-start.component';
import { RecipeEditComponent } from './recipes/recipe-edit/recipe-edit.component';
import { RecipeService } from './recipes/recipe.service';
import { RecipeTypeComponent } from './recipe-type/recipe-type.component';
import { RecipeTypeListsComponent } from './recipe-type/recipe-type-lists/recipe-type-lists.component';
import { RecipeTypeItemComponent } from './recipe-type/recipe-type-lists/recipe-type-item/recipe-type-item.component';
import { RecipeTypeDetailComponent } from './recipe-type/recipe-type-detail/recipe-type-detail.component';
import { RecipeTypeEditComponent } from './recipe-type/recipe-type-edit/recipe-type-edit.component';
import { RecipeTypeStartComponent } from './recipe-type/recipe-type-start/recipe-type-start.component';
import { RecipeTypeService } from './recipe-type/recipe-type.service';
import { OrdersComponent } from './orders/orders.component';
import { OrderControllerComponent } from './orders/order-controller/order-controller.component';
import { ReportsComponent } from './reports/reports.component';
import { ReportsService } from './reports/reports.service';
import { OrdersListComponent } from './orders/orders-list/orders-list.component';
import { LoginComponent } from './login/login.component';
import { AdminOrdersComponent } from './admin-orders/admin-orders.component';
import { OrdersService } from './orders/orders.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RecipesComponent,
    RecipeListComponent,
    RecipeDetailComponent,
    RecipeItemComponent,
    DropdownDirective,
    RecipeStartComponent,
    RecipeEditComponent,
    RecipeTypeComponent,
    RecipeTypeListsComponent,
    RecipeTypeItemComponent,
    RecipeTypeDetailComponent,
    RecipeTypeEditComponent,
    RecipeTypeStartComponent,
    OrdersComponent,
    OrderControllerComponent,
    ReportsComponent,
    OrdersListComponent,
    LoginComponent,
    AdminOrdersComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [RecipeService, RecipeTypeService, ReportsService, OrdersService],
  bootstrap: [AppComponent]
})
export class AppModule {}
