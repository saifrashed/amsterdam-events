import {ComponentFixture, TestBed} from '@angular/core/testing';

import {NavBarSbComponent} from './nav-bar-sb.component';

describe('NavBarComponent', () => {
    let component: NavBarSbComponent;
    let fixture: ComponentFixture<NavBarSbComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [NavBarSbComponent]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(NavBarSbComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
