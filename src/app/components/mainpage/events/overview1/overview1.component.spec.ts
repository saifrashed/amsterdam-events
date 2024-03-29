import {ComponentFixture, TestBed} from '@angular/core/testing';

import {Overview1Component} from './events.component';

describe('EventsComponent', () => {
    let component: Overview1Component;
    let fixture: ComponentFixture<Overview1Component>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [Overview1Component]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(Overview1Component);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
