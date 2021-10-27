import {Injectable} from '@angular/core';
import {AEvent} from "../models/a-event";

@Injectable({
    providedIn: 'root'
})
export class AEventsService {

    public aEvents: AEvent[];

    constructor() {
        this.aEvents = [];
        for (let i = 0; i < 9; i++) {
            this.addRandomAEvent();
        }
    }

    findAll(): AEvent[] {
        return this.aEvents;
    }

    findById(eId: number): AEvent {
        var eventIndex = this.aEvents.map(item => item.id).indexOf(eId);

        return this.aEvents[eventIndex];
    }

    save(aEvent: AEvent): AEvent {
        var saveIndex = this.aEvents.map(item => item.id).indexOf(aEvent.id);
        const previousEvent = this.aEvents[saveIndex];

        this.aEvents[saveIndex] = aEvent;

        return previousEvent;
    }

    deleteById(eId: number): AEvent {

        var removeIndex = this.aEvents.map(item => item.id).indexOf(eId);
        const deletedEvent = this.aEvents[removeIndex];

        this.aEvents.splice(removeIndex, 1);

        return deletedEvent;
    }

    /**
     * Adds a random event
     */
    addRandomAEvent(): void {
        this.aEvents.push(AEvent.createRandomAEvent());
    }


}
