import {Component, OnInit} from '@angular/core';
import {AEvent} from "../../../../models/a-event";


@Component({
    selector: 'app-overview-one',
    templateUrl: './overview1.component.html',
    styleUrls: ['./overview1.component.css']
})
export class Overview1Component implements OnInit {

    public aEvents: AEvent[];

    constructor() {
        this.aEvents = [];
    }

    ngOnInit(): void {
        for (let i = 0; i < 9; i++) {
            this.addRandomAEvent();
        }

        console.log(this.aEvents)
    }

    addRandomAEvent(): void {
        this.aEvents.push(AEvent.createRandomAEvent())
    }



}
