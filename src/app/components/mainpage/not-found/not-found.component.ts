import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
    selector: 'app-not-found',
    templateUrl: './not-found.component.html',
    styleUrls: ['./not-found.component.css']
})
export class NotFoundComponent implements OnInit {

    public notFoundURL: any;

    constructor(private router: Router) {
    }

    ngOnInit(): void {
        this.notFoundURL = this.router.url;
    }

}
