import {Component, OnInit} from '@angular/core';
import {AEventsService} from "../../../../services/a-events-sb";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-overview-five',
    templateUrl: '../overview4/overview4.component.html',
    styleUrls: ['../overview4/overview4.component.css'],
})

export class Overview5Component implements OnInit {

    constructor(public AEventsService: AEventsService, public router: Router, public activatedRoute: ActivatedRoute) {
    }

    /**
     * Initialise method
     */
    ngOnInit(): void {
    }

    /**
     * Selects an event
     */
    selectAEvent(eId: number): void {
        this.router.navigate([eId], {relativeTo: this.activatedRoute})
    }
}
