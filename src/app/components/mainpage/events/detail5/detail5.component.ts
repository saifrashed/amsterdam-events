import {Component, OnInit} from '@angular/core';
import {AEventsService} from "../../../../services/a-events-sb.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {FormControl, FormGroup} from "@angular/forms";
import {AEvent, AEventStatus} from "../../../../models/a-event";

@Component({
    selector: 'app-detail-five',
    templateUrl: '../detail4/detail4.component.html',
    styleUrls: ['../detail4/detail4.component.css'],
})
export class Detail5Component implements OnInit {
    eventForm = new FormGroup({
        id: new FormControl(''),
        title: new FormControl(''),
        description: new FormControl(''),
        status: new FormControl(''),
        isTicketed: new FormControl(''),
        participationFee: new FormControl(''),
        maxParticipants: new FormControl(''),
    });
    public editedAEventId: any;
    public statusOptions: Array<string> = ['DRAFT', 'PUBLISHED', 'CANCELED'];
    protected childParamsSubscription: Subscription = null as any;

    constructor(public AEventsService: AEventsService, public router: Router, public activatedRoute: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.childParamsSubscription = this.activatedRoute.params.subscribe((params: Params) => {
            console.log("Detail setup id=" + params["id"]);

            this.setEditedAEventId(params["id"] || -1);
            this.getAEvent();
        });

    }

    ngOnDestroy(): void {
        this.childParamsSubscription && this.childParamsSubscription.unsubscribe();
    }

    setEditedAEventId(eId: any): void {
        this.editedAEventId = eId;
    }

    getAEvent(): void {
        if (this.editedAEventId != -1) {

            let event = this.AEventsService.findById(parseInt(this.editedAEventId));

            console.log(parseInt(this.editedAEventId));

            this.eventForm.setValue({
                id: event.id,
                title: event.title,
                description: event.description,
                status: event.status,
                isTicketed: event.isTicketed,
                participationFee: event.participationFee,
                maxParticipants: event.maxParticipants,
            })
        }
    }

    deleteAEvent(eId: number): void {
        if (confirm("Are you sure you want to delete " + this.eventForm.controls["title"].value)) {
            this.AEventsService.deleteById(eId);
            this.router.navigate(["../"], {relativeTo: this.activatedRoute})
        }
    }

    saveAEvent() {
        this.AEventsService.save(Object.assign(new AEvent(0, "", new Date(), new Date(), "", AEventStatus.Draft, false, 0, 0), this.eventForm.value));
        this.eventForm.markAsPristine();
        this.getAEvent();
    }

    clearAEvent(): void {
        if (confirm("Are you sure you want to clear " + this.eventForm.controls["title"].value)) {
            this.eventForm.patchValue({
                title: "",
                description: "",
                status: "",
                isTicketed: "",
                participationFee: "",
                maxParticipants: "",
            });
        }
    }

    reset(): void {
        if (confirm("Are you sure you want to reset " + this.eventForm.controls["title"].value)) {
            this.getAEvent()
        }
    }

    cancel(): void {
        if (confirm("Are you sure you want to cancel " + this.eventForm.controls["title"].value)) {
            this.router.navigate(["../"], {relativeTo: this.activatedRoute})
        }
    }

}
