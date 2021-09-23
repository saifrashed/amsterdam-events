import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AEventsService} from "../../../../services/a-events.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-detail-three',
    templateUrl: './detail3.component.html',
    styleUrls: ['./detail3.component.css'],
})
export class Detail3Component implements OnInit {

    @Input() editedAEventId: any;
    @Output() editedAEventIdChange = new EventEmitter();

    public editableAEvent: any;
    public statusOptions: Array<string> = ['DRAFT','PUBLISHED','CANCELED'];

    constructor(private AEventsService: AEventsService) {
        this.editableAEvent = AEventsService.findById(this.editedAEventId)
    }

    ngOnInit(): void {
        this.getAEvent();
    }

    ngOnChanges(): void {
        this.getAEvent();
    }


    getAEvent(): void {
        this.editableAEvent = this.AEventsService.findById(this.editedAEventId).trueCopy();
    }

    deleteAEvent(eId: number): void {
        this.AEventsService.deleteById(eId);
        this.reselect(-1)
    }

    saveAEvent() {
        console.log(this.editableAEvent)
        console.log(this.AEventsService.findById(this.editableAEvent.id))

    }

    reselect(eId: number) {
        this.editedAEventId = eId;
        this.editedAEventIdChange.emit(this.editedAEventId)
    }
}
