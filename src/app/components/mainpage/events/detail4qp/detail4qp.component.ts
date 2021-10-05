import {Component, HostListener, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Detail4Component} from "../detail4/detail4.component";
import {Params} from "@angular/router";


@Component({
    selector: 'app-detail-four',
    templateUrl: '../detail4/detail4.component.html',
    styleUrls: ['../detail4/detail4.component.css'],
})
export class Detail4qpComponent extends Detail4Component implements OnInit {

    ngOnInit(): void {
        this.childParamsSubscription = this.activatedRoute.queryParams.subscribe((params: Params) => {
            console.log("Detail setup id=" + params["id"]);

            this.setEditedAEventId(params["id"] || -1);
            this.getAEvent();
        });
    }

    ngOnDestroy(): void {
        this.childParamsSubscription && this.childParamsSubscription.unsubscribe();
    }

    @HostListener('window:beforeunload')
    canDeactivate(): Observable<boolean> | boolean {
        console.log(this.eventForm.dirty);
        if (this.eventForm.dirty) {
            if (confirm("Are you sure you want to discard changes?")) {
                this.eventForm.markAsPristine();
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
