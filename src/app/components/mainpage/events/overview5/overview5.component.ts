import {Component, OnInit} from '@angular/core';
import {AEventsService} from "../../../../services/a-events-sb.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AEvent} from "../../../../models/a-event";

@Component({
    selector: 'app-overview-five',
    templateUrl: '../overview4/overview4.component.html',
    styleUrls: ['../overview4/overview4.component.css'],
})

export class Overview5Component implements OnInit {

    public events: AEvent[] = [];

    constructor(public AEventsService: AEventsService, public router: Router, public activatedRoute: ActivatedRoute) {
        this.AEventsService.restGetAEvents().subscribe(events => {
            this.events = events;
        });
    }

    /**
     * Initialise method
     */
    ngOnInit(): void {
        this.getEvents();
    }

    onAddEvent(): void{
        this.AEventsService.addRandomAEvent().subscribe(event => {
            this.selectAEvent(event.id);
            this.getEvents();
            console.log(event)
        })
    }

    getEvents(): void {
         this.AEventsService.restGetAEvents().subscribe(events => {
             this.events = events;
         });
    }


    /**
     * Selects an event
     */
    selectAEvent(eId: number): void {
        console.log(eId);
        this.router.navigate([eId], {relativeTo: this.activatedRoute})
    }
}
