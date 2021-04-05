import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RecipesComponent } from './recipes/recipes.component';
import { RecipeStartComponent } from './recipes/recipe-start/recipe-start.component';
import { RecipeDetailComponent } from './recipes/recipe-detail/recipe-detail.component';
import { RecipeEditComponent } from './recipes/recipe-edit/recipe-edit.component';
import { RecipesResolverService } from './recipes/recipes-resolver.service';
import { RecipeTypeComponent } from './recipe-type/recipe-type.component';
import { RecipeTypeEditComponent } from './recipe-type/recipe-type-edit/recipe-type-edit.component';
import { RecipeTypeStartComponent } from './recipe-type/recipe-type-start/recipe-type-start.component';
import { OrdersComponent } from './orders/orders.component';
import { ReportsComponent } from './reports/reports.component';
import { AdminOrdersComponent } from './admin-orders/admin-orders.component';
import { OrdersDetailComponent } from './orders/orders-detail/orders-detail.component';
import { OrdersControllerComponent } from './orders-controller/orders-controller.component';
import { OrdersControllerDetailComponent } from './orders-controller/orders-controller-detail/orders-controller-detail.component';
import { RecipeTypesResolverService } from './recipe-type/recipe-type-resolver.service';
import { AdminOrdersResolver } from './admin-orders/admin-orders-resolver.service';
import { OrdersResolver } from './orders/orders-resolver.service';
import { LoginComponent } from './login/login.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  {
    path: 'recipes',
    component: RecipesComponent,
    resolve: [RecipesResolverService],
    children: [
      { path: '', component: RecipeStartComponent },
      { path: 'new', component: RecipeEditComponent },
      {
        path: ':id',
        component: RecipeDetailComponent,
        resolve: [RecipesResolverService]
      },
      {
        path: ':id/edit',
        component: RecipeEditComponent,
        resolve: [RecipesResolverService]
      }
    ]
  },

  { path: 'recipe-type', 
  component: RecipeTypeComponent,
  resolve: [RecipeTypesResolverService],
    children: [
      { path: '', component: RecipeTypeStartComponent },
      { path: 'new', component: RecipeTypeEditComponent },
      {
        path: ':id',
        component: RecipeTypeEditComponent,
        resolve: [RecipeTypesResolverService],
      },
      {
        path: ':id/edit',
        component: RecipeTypeEditComponent,
        resolve: [RecipeTypesResolverService],
      }
    ] },

  { path: 'orders', component: OrdersComponent,
  resolve: [OrdersResolver], 
    children: [
      {
        path: ':id',
        component: OrdersDetailComponent
      },

    ]},
  { path: 'orders-controller', component: OrdersControllerComponent,
  resolve: [AdminOrdersResolver],
  children: [
    {
      path: ':id',
      component: OrdersControllerDetailComponent
    },
  ] },
  { path: 'reports', component: ReportsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin-orders', component: AdminOrdersComponent, resolve: [AdminOrdersResolver]  },



];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
