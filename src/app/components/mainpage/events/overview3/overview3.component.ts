import {Component, OnInit} from '@angular/core';
import {AEvent} from "../../../../models/a-event";
import {AEventsService} from "../../../../services/a-events.service";

@Component({
    selector: 'app-overview-three',
    templateUrl: './overview3.component.html',
    styleUrls: ['./overview3.component.css'],
})

export class Overview3Component implements OnInit {

    public selectedAEventId: any = -1;

    constructor(public AEventsService: AEventsService) {
    }

    /**
     * Initialise method
     */
    ngOnInit(): void {

    }

    /**
     * Selects an event
     * @param selectedAEvent
     */
    selectAEvent(selectedAEvent: AEvent): void {
        this.selectedAEventId = selectedAEvent.id;
    }
}
