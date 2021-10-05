import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot} from "@angular/router";

export interface CanComponentDeactivate {
    canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

@Injectable({
    providedIn: 'root'
})
export class CanDeactivateGuard implements CanDeactivate<CanComponentDeactivate> {
    canDeactivate(component: CanComponentDeactivate,
                  route: ActivatedRouteSnapshot,
                  state: RouterStateSnapshot) {

        let url: string = state.url;
        console.log('Url: ' + url);

        return component.canDeactivate ? component.canDeactivate() : true;
    }
}
