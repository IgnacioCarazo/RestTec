import { Component, OnChanges, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrdersService } from '../orders/orders.service';
import { DataStorageService } from '../shared/data-storage.service';
import { User } from '../shared/user.model';
import { HeaderService } from './header.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})

export class HeaderComponent implements OnInit {
    isAdmin = true;
    private login: boolean;
    email: string;
    password: string;
    user: User;
    
    /**
    @constructor
    */
    constructor(private dataStorageService: DataStorageService,
        private route: ActivatedRoute,
        private router: Router,
        private headerService: HeaderService,
        private orderService: OrdersService){
        this.login = dataStorageService.isAuthorized;
    }

    ngOnInit() {
        
    }

    /**
    * @name changeView()
    * @description When the user changes the page's view with the respective button this method is called and changes 
    * the value of this.isAdmin' which is true when in Admin View and false when in Chef View.
    */
    changeView() {
        this.isAdmin = !this.isAdmin;
    }

    /**
    * @name logout() 
    * @description If the user is logged in any view and logs out with the respective button to do so this method will 
    * be called. It changes the valie of 'login' to false which indicates there's no one logged in and changes the
    * web link to '/login'.
    */
    logout() {
        this.dataStorageService.isAuthorized = false;
        this.login = this.dataStorageService.isAuthorized;
        this.router.navigate(['/login']);
    }

    /**
    * @name onSubmit() 
    * @argument {NgForm} form - A form argument which is filled with the user's email and password
    * @description When the button to login is clicked this method is called and it sets the actual user in the page which
    * is returned by the backend. 
    */
    onSubmit(form: NgForm) {
        this.email = form.value.email;
        this.password = form.value.password;        
        this.dataStorageService.sendLoginInfo(form.value.email,form.value.password, this.isAdmin).
            subscribe( user => {
                this.user = user;
                this.headerService.setUser(this.user);
                if (this.user.credentials) {
                    this.userAdmitted(this.user.access);
                }                
            });
        form.reset();

        

      }

    /**
    * @name userAdmitted()
    * @argument {boolean} access - If true the user is an admin, if false the user is a chef.
    * @description It determines wheter the user logged is a chef, an admin or an unregistered user. Depending on which type
    * of user it is it sets the web's link respectively.
    */
    userAdmitted(access: boolean) {
        // User is Admin
        if (this.isAdmin && access) {
            console.log(this.user);
            this.dataStorageService.isAuthorized = true;
            this.login = this.dataStorageService.isAuthorized;
            this.router.navigate(['/admin-orders']);
        // User is Chef
        } else if (!this.isAdmin && !access){
            this.orderService.setAssignedOrders(this.user.name);
            this.orderService.setUnAssignedOrders(this.user.name);
            this.dataStorageService.isAuthorized = true;
            this.login = this.dataStorageService.isAuthorized;
            this.router.navigate(['/orders-controller'])
        // User is unregistered
        } else {
            this.router.navigate(['/login'])
        }    
    }

}