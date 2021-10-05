import {Component, HostListener, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Detail4Component} from "../detail4/detail4.component";


@Component({
    selector: 'app-detail-four',
    templateUrl: '../detail4/detail4.component.html',
    styleUrls: ['../detail4/detail4.component.css'],
})
export class Detail41Component extends Detail4Component implements OnInit {
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
