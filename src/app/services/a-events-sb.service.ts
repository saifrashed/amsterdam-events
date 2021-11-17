import {Injectable} from '@angular/core';
import {AEvent, AEventStatus} from "../models/a-event";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class AEventsService {

    apiURL = 'http://localhost:8084/api';

    public aEvents: AEvent[];

    constructor(private http: HttpClient) {
        this.aEvents = [];
        this.reload()
    }

    reload(): void {
        this.restGetAEvents().subscribe(result => {
            result.forEach(event => {
                this.aEvents.push(AEvent.trueCopy(event))
            })
        });
    }

    findAll(): AEvent[] {
        return this.aEvents;
    }

    findById(eId: number): AEvent {

        for (let i = 0; i < this.aEvents.length; i++) {
            if (eId == this.aEvents[i].id) {
                return this.aEvents[i];
            }
        }

        return new AEvent(0, "", new Date(), new Date(), "", AEventStatus.Draft, false, 0, 0)
    }

    save(aEvent: AEvent): AEvent {
        var saveIndex = this.aEvents.map(item => item.id).indexOf(aEvent.id);
        const previousEvent = this.aEvents[saveIndex];

        this.aEvents[saveIndex] = aEvent;

        this.restPutAEvent(aEvent).subscribe(result => {
            console.log(result)
        });

        return previousEvent;
    }

    deleteById(eId: number): AEvent {

        var removeIndex = this.aEvents.map(item => item.id).indexOf(eId);
        const deletedEvent = this.aEvents[removeIndex];


        this.restDeleteAEvent(eId).subscribe(result => {
            this.aEvents.splice(removeIndex, 1);
        });

        return deletedEvent;
    }

    /**
     * Adds a random event
     */
    addRandomAEvent(): void {
        let event = AEvent.createRandomAEvent();
        event.id = 0;

        this.restPostAEvent(event).subscribe(result => {
            this.aEvents.push(AEvent.trueCopy(result));
        });
    }


    /**
     * Rest calls
     */
    restGetAEvents(): Observable<AEvent[]> {
        return this.http.get<AEvent[]>(this.apiURL + '/aevent')
    }

    restPostAEvent(aEvent: AEvent): Observable<AEvent> {
        return this.http.post<AEvent>(this.apiURL + '/aevent', aEvent)
    }

    restPutAEvent(aEvent: AEvent): Observable<AEvent> {
        return this.http.put<AEvent>(this.apiURL + '/aevent/' + aEvent.id, aEvent)
    }

    restDeleteAEvent(aEventId: number): Observable<AEvent> {
        return this.http.delete<AEvent>(this.apiURL + '/aevent/' + aEventId)
    }


}
