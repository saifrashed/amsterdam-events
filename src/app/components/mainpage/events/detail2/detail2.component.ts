import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-detail-two',
  templateUrl: './detail2.component.html',
  styleUrls: ['./detail2.component.css']
})
export class Detail2Component implements OnInit {

  @Input() detailEvent: any; // decorate the property with @Input()

  @Output() outputEvent:EventEmitter<string>= new EventEmitter();

  constructor() { }

  ngOnInit(): void {

  }



}
