import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module'; // CLI imports AppRoutingModule
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/mainpage/header/header.component';
import {HomeComponent} from './components/mainpage/home/home.component';
import {NavBarComponent} from './components/mainpage/nav-bar/nav-bar.component';
import {Overview1Component} from './components/mainpage/events/overview1/overview1.component';
import {Overview2Component} from './components/mainpage/events/overview2/overview2.component';
import {Detail2Component} from './components/mainpage/events/detail2/detail2.component';
import {Overview3Component} from './components/mainpage/events/overview3/overview3.component';
import {Detail3Component} from './components/mainpage/events/detail3/detail3.component';
import {Overview4qpComponent} from './components/mainpage/events/overview4qp/overview4qp.component';
import {Overview4Component} from './components/mainpage/events/overview4/overview4.component';

import {Detail4Component} from './components/mainpage/events/detail4/detail4.component';
import {Detail41Component} from './components/mainpage/events/detail41/detail41.component';
import {Detail4qpComponent} from './components/mainpage/events/detail4qp/detail4qp.component';

import {NotFoundComponent} from './components/mainpage/not-found/not-found.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        HomeComponent,
        NavBarComponent,
        Overview1Component,
        Overview2Component,
        Detail2Component,
        Overview3Component,
        Overview4Component,
        Overview4qpComponent,
        Detail3Component,
        Detail4Component,
        Detail41Component,
        Detail4qpComponent,
        NotFoundComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        AppRoutingModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
