import {Injectable} from '@angular/core';
import {AEvent} from "../models/a-event";
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {catchError, map, retry} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AEventsService {

    apiURL = 'http://localhost:8084/api';

    public aEvents: Observable<AEvent[]>;

    constructor(private http: HttpClient) {
       this.aEvents = this.restGetAEvents();
    }

    restGetAEvents(): Observable<AEvent[]> {
        return this.http.get<AEvent[]>(this.apiURL + '/aevent')
            .pipe(
                map(aevent => {
                    return aevent.map(value => {
                        return AEvent.trueCopy(value)
                    });
                }),
                retry(1),
                catchError(this.handleError)
            )
    }

    restPostAEvent(aEvent: AEvent): Observable<AEvent> {
        return this.http.post<AEvent>(this.apiURL + '/aevent', aEvent)
            .pipe(
                map(aevent => {
                    console.log(aevent);

                    return AEvent.trueCopy(aevent)
                }),
                retry(1),
                catchError(this.handleError)
            );
    }

    restPutAEvent(aEvent: AEvent): Observable<AEvent> {

        console.log(JSON.stringify(aEvent));

        return this.http.put<AEvent>(this.apiURL + '/aevent/' + aEvent.id, aEvent)
            .pipe(
                map(aevent => {
                    return AEvent.trueCopy(aevent)
                }),
                retry(1),
                catchError(this.handleError)
            );
    }

    restDeleteAEvent(aEventId: number): void {
        this.http.delete<AEvent>(this.apiURL + '/aevent/' + aEventId)
            .pipe(
                map(aevent => {
                    return AEvent.trueCopy(aevent)
                }),
                retry(1),
                catchError(this.handleError)
            ).subscribe(data => {
            this.aEvents = this.restGetAEvents();
        });
    }


    findAll(): Observable<AEvent[]> {
        return this.restGetAEvents();
    }

    findById(eId: number): Observable<AEvent> {
        return this.http.get<AEvent>(this.apiURL + '/aevent/' + eId)
            .pipe(
                map(aevent => {
                    return AEvent.trueCopy(aevent)
                }),
                retry(1),
                catchError(this.handleError)
            );

    }


    deleteById(eId: number): void {
        this.restDeleteAEvent(eId);

        this.restGetAEvents().subscribe(value => {
            this.aEvents = this.restGetAEvents();
        });
    }

    /**
     * Adds a random event
     */
    addRandomAEvent(): Observable<AEvent> {

        let event = AEvent.createRandomAEvent();

        event.id = 0;

        return this.restPostAEvent(event);
    }


    // Error handling
    handleError(error: any) {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
            // Get client-side error
            errorMessage = error.error.message;
        } else {
            // Get server-side error
            errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
        }
        console.log(errorMessage);
        return throwError(errorMessage);
    }


}
