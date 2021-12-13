import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {SessionSbService} from "./SessionSbService";
import {Observable} from "rxjs";

@Injectable()
export class AuthSbInterceptor implements HttpInterceptor {

    constructor(private sessionManager: SessionSbService) {

    }


    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let token = this.sessionManager.getTokenFromSessionStorage();

        if (token != null) {

            req = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${token}`
                }
            });

            return next.handle(req);
        } else {
            return next.handle(req);
        }

    }
}

