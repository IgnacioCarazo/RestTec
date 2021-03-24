import { Component, OnChanges, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataStorageService } from '../shared/data-storage.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})

export class HeaderComponent implements OnInit {
    isAdmin = true;
    private login: boolean;

    constructor(private dataStorageService: DataStorageService,
        private route: ActivatedRoute,
        private router: Router){
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
        this.dataStorageService.isAuthorized = true;
        this.login = this.dataStorageService.isAuthorized;
        console.log(form.value);
        form.reset();
        if (this.isAdmin) {
            this.router.navigate(['/admin-orders']);
        } else{

        }

      }

}