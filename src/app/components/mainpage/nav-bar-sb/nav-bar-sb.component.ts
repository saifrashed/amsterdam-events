import {Component, OnInit} from '@angular/core';
import {SessionSbService} from "../../../services/SessionSbService";

@Component({
    selector: 'app-nav-bar-sb',
    templateUrl: './nav-bar-sb.component.html',
    styleUrls: ['./nav-bar-sb.component.css']
})
export class NavBarSbComponent implements OnInit {

    constructor(public sessionManager: SessionSbService) {
    }

    ngOnInit(): void {
    }

    logout() {
        this.sessionManager.signOff();
    }

}
