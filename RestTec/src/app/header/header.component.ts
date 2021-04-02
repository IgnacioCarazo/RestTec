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
    private check: boolean = false;
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
        this.dataStorageService.isAuthorized = true;
        this.login = this.dataStorageService.isAuthorized;
        console.log(this.headerService.user);
        console.log(form.value);
        this.user = this.dataStorageService.sendLoginInfo(form.value.email,form.value.password);
        form.reset();
        if (this.isAdmin) {
            this.router.navigate(['/admin-orders']);
        } else{
            this.router.navigate(['/orders-controller'])
        }

      }

    checkUser() {
        this.dataStorageService.sendLoginInfo(this.email,this.password);
        console.log(123456);
        this.check = true;

        
    }

}