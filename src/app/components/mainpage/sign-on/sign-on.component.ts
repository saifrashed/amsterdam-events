import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {SessionSbService} from "../../../services/SessionSbService";

@Component({
  selector: 'app-sign-on',
  templateUrl: './sign-on.component.html',
  styleUrls: ['./sign-on.component.css']
})
export class SignOnComponent implements OnInit {

  public email: string | any = null;
  public password: string | any = null;


  constructor(private router: Router, public sessionManager: SessionSbService) { }

  ngOnInit(): void {

  }


  signIn(email: string, password: string) {

    console.log(email, password)

    this.sessionManager.signOn(email, password).subscribe(result => {
      console.log(result)
    });

    this.email = null;
    this.password = null;
  }

}
