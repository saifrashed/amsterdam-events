import {Component, OnInit} from '@angular/core';
import {AEventsService} from "../../../../services/a-events-sb.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AEvent} from "../../../../models/a-event";
import {SessionSbService} from "../../../../services/SessionSbService";

@Component({
    selector: 'app-overview-five',
    templateUrl: '../overview4/overview4.component.html',
    styleUrls: ['../overview4/overview4.component.css'],
})

export class Overview5Component implements OnInit {

    public events: AEvent[] = [];


    constructor(public AEventsService: AEventsService, public router: Router, public activatedRoute: ActivatedRoute, public sessionManager: SessionSbService) {
    }


    ngOnInit(): void {
        console.log(this.sessionManager.getTokenFromSessionStorage());
        this.getEvents();
    }

    onAddEvent(): void {
        this.AEventsService.addRandomAEvent()
    }

    getEvents(): void {
        this.events = this.AEventsService.findAll();
    }

    selectAEvent(eId: number): void {
        this.router.navigate([eId], {relativeTo: this.activatedRoute})
    }
}
