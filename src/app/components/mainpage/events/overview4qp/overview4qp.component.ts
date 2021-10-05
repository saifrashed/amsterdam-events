import {Component, OnInit} from '@angular/core';
import {Overview4Component} from "../overview4/overview4.component";

@Component({
    selector: 'app-overview-four-qp',
    templateUrl: '../overview4/overview4.component.html',
    styleUrls: ['../overview4/overview4.component.css'],
})

export class Overview4qpComponent extends Overview4Component implements OnInit {

    /**
     * Selects an event
     */
    selectAEvent(eId: number): void {
        this.router.navigate(["edit"], {relativeTo: this.activatedRoute, queryParams: {id: eId}})
    }
}
