import {Component, OnInit} from '@angular/core';
import {AEventsService} from "../../../../services/a-events.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AEvent} from "../../../../models/a-event";

@Component({
    selector: 'app-overview-four',
    templateUrl: './overview4.component.html',
    styleUrls: ['./overview4.component.css'],
})

export class Overview4Component implements OnInit {

    public events: AEvent[] = [];


    constructor(public AEventsService: AEventsService, public router: Router, public activatedRoute: ActivatedRoute) {
    }

    /**
     * Initialise method
     */
    ngOnInit(): void {
        this.getEvents();
    }

    onAddEvent(): void{
        this.AEventsService.addRandomAEvent()
    }

    getEvents(): void {
        this.events = this.AEventsService.findAll();
    }

    /**
     * Selects an event
     */
    selectAEvent(eId: number): void {
        this.router.navigate([eId], {relativeTo: this.activatedRoute})
    }
}
