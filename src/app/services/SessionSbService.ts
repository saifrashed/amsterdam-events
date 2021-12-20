import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";
import {shareReplay} from "rxjs/operators";

@Injectable({
    providedIn: 'root'
})
export class SessionSbService {

    public readonly BACKEND_AUTH_URL = "http://localhost:8084/api/authenticate";

    public currentUserName: string | any;

    constructor(private http: HttpClient) {

        if(this.isAuthenticated()) {
            this.currentUserName = sessionStorage.getItem("userName")
        } else {
            this.currentUserName = "Visitor"
        }

    }

    signOn(eMail: string, password: string): Observable<any> {
        console.log("login " + eMail + " / " + password);
        let signInResponse = this.http.post<HttpResponse<User>>(this.BACKEND_AUTH_URL + "/login",
            {eMail: eMail, passWord: password},
            {observe: "response"}).pipe(shareReplay(1));

        signInResponse.subscribe(response => {
            console.log(response);

            this.saveTokenIntoSessionStorage(response.headers.get("Authorization"), (response.body as unknown as User).name)
        }, error => {
            console.log(error);
            this.saveTokenIntoSessionStorage(null, null)
        });

        return signInResponse;
    }

    signOff() {
        this.currentUserName = "Visitor";
        sessionStorage.clear();
    }

    isAuthenticated(): boolean {

        if (sessionStorage.getItem("token") != undefined && sessionStorage.getItem("userName") != undefined) {
            return true;
        } else {
            return false;
        }
    }

    getTokenFromSessionStorage(): string | null {
        this.currentUserName = sessionStorage.getItem("userName");
        return sessionStorage.getItem("token")
    }

    saveTokenIntoSessionStorage(token: string | any, username: string | any) {
        this.currentUserName = username;
        sessionStorage.setItem("token", token);
        sessionStorage.setItem("userName", username);
    }


}
