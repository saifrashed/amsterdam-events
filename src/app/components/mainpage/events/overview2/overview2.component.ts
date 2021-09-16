import {Component, OnInit} from '@angular/core';
import {AEvent} from "../../../../models/a-event";

@Component({
    selector: 'app-overview-two',
    templateUrl: './overview2.component.html',
    styleUrls: ['./overview2.component.css']
})

export class Overview2Component implements OnInit {

    public aEvents: AEvent[];
    public selectedAEvent: any;

    constructor() {
        this.aEvents = [];
    }

    /**
     * Initialise method
     */
    ngOnInit(): void {
        for (let i = 0; i < 9; i++) {
            this.addRandomAEvent();
        }

        console.log(this.aEvents)
    }


    /**
     * Adds a random event
     */
    addRandomAEvent(): void {
        let newEvent = AEvent.createRandomAEvent();

        this.aEvents.push(newEvent);
        this.selectedAEvent = newEvent;
    }


    /**
     * Selects an event
     * @param selectedAEvent
     */
    selectAEvent(selectedAEvent: AEvent): void {
        console.log(selectedAEvent.title);

        this.selectedAEvent = selectedAEvent;
    }

}
