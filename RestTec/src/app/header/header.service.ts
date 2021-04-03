import { Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { User } from "../shared/user.model";

@Injectable()
export class HeaderService {
    userChanged = new Subject<User>();
    user: User;


    getUser() {
        return this.user;
    }

    setUser(newUser: User) {
        this.user = newUser;
        this.userChanged.next(this.user);
    }
}