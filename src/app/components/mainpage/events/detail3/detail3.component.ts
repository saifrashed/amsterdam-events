import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AEventsService} from "../../../../services/a-events.service";
import {FormControl, FormGroup} from "@angular/forms";
import {AEvent} from "../../../../models/a-event";

@Component({
  selector: 'app-detail-three',
  templateUrl: './detail3.component.html',
  styleUrls: ['./detail3.component.css'],
})
export class Detail3Component implements OnInit {

  @Input() editedAEventId: any;
  @Output() editedAEventIdChange = new EventEmitter();

  public editableAEvent: any;
  public statusOptions: Array<string> = ['DRAFT', 'PUBLISHED', 'CANCELED'];

  constructor(private AEventsService: AEventsService) {
    this.editableAEvent = AEventsService.findById(this.editedAEventId)
  }

  ngOnInit(): void {
    this.getAEvent();
  }

  ngOnChanges(): void {
    this.getAEvent();
  }

  click():void {
    if (confirm("Are you sure to delete " + this.editableAEvent.title)) {
      console.log("Delete Title");
    }
  }

  getAEvent(): void {
    if (this.editedAEventId != -1) {
      this.editableAEvent = this.AEventsService.findById(this.editedAEventId).trueCopy();
    }
  }

  deleteAEvent(eId: number): void {
    if (confirm("Are you sure you want to delete " + this.editableAEvent.title)) {
      this.AEventsService.deleteById(eId);
      this.reselect(-1)
    }
  }

  saveAEvent() {
    this.AEventsService.save(this.editableAEvent)
  }

  clearAEvent(): void {
    this.editableAEvent.title = ""
    this.editableAEvent.description = ""
    this.editableAEvent.status = ""
    this.editableAEvent.isTicketed = ""
    this.editableAEvent.participationFee = ""
    this.editableAEvent._maxParticipants = ""
  }

  reset(): void {
    this.getAEvent()
  }

  cancel(): void {
    this.reselect(-1)
  }

  reselect(eId: number) {
    this.editedAEventId = eId;
    this.editedAEventIdChange.emit(this.editedAEventId)
  }
}
