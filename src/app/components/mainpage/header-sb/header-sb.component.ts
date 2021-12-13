import { Component, OnInit } from '@angular/core';
import {SessionSbService} from "../../../services/SessionSbService";

@Component({
  selector: 'app-header-sb',
  templateUrl: './header-sb.component.html',
  styleUrls: ['./header-sb.component.css']
})
export class HeaderSbComponent implements OnInit {

  public currentDate: Date = new Date();

  constructor(public sessionManager: SessionSbService) { }

  ngOnInit(): void {
  }

}
