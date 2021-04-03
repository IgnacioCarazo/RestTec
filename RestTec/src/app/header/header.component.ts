import { Component, OnChanges, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
    

    constructor(private dataStorageService: DataStorageService,
        private route: ActivatedRoute,
        private router: Router,
        private headerService: HeaderService){
        this.login = dataStorageService.isAuthorized;
    }

    ngOnInit() {
        
    }

    changeView() {
        this.isAdmin = !this.isAdmin;
    }

    logout() {
        this.dataStorageService.isAuthorized = false;
        this.login = this.dataStorageService.isAuthorized;
        this.router.navigate(['/login']);
    }

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

    userAdmitted(access: boolean) {
        if (this.isAdmin && access) {
            console.log(this.user);

            this.dataStorageService.isAuthorized = true;
            this.login = this.dataStorageService.isAuthorized;
            this.router.navigate(['/admin-orders']);
        } else if (!this.isAdmin && !access){
            this.dataStorageService.isAuthorized = true;
            this.login = this.dataStorageService.isAuthorized;
            this.router.navigate(['/orders-controller'])
        } else {
            console.log(this.user);
            
            this.router.navigate(['/login'])

        }

        
    }

}