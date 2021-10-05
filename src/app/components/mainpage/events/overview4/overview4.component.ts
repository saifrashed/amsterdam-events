import {Component, OnInit} from '@angular/core';
import {AEventsService} from "../../../../services/a-events.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-overview-four',
    templateUrl: './overview4.component.html',
    styleUrls: ['./overview4.component.css'],
})

export class Overview4Component implements OnInit {

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
