import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module'; // CLI imports AppRoutingModule
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/mainpage/header/header.component';
import {HeaderSbComponent} from './components/mainpage/header-sb/header-sb.component';
import {HomeComponent} from './components/mainpage/home/home.component';
import {NavBarComponent} from './components/mainpage/nav-bar/nav-bar.component';
import {NavBarSbComponent} from './components/mainpage/nav-bar-sb/nav-bar-sb.component';

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

import {Detail5Component} from './components/mainpage/events/detail5/detail5.component';
import {Overview5Component} from './components/mainpage/events/overview5/overview5.component';

import {SignOnComponent} from './components/mainpage/sign-on/sign-on.component';


import {NotFoundComponent} from './components/mainpage/not-found/not-found.component';


@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        HomeComponent,
        NavBarComponent,
        NavBarSbComponent,
        Overview1Component,
        Overview2Component,
        Detail2Component,
        Overview3Component,
        Overview4Component,
        Overview4qpComponent,
        Overview5Component,
        Detail3Component,
        Detail4Component,
        Detail41Component,
        Detail4qpComponent,
        Detail5Component,
        SignOnComponent,
        NotFoundComponent,
        HeaderSbComponent
    ],
    imports: [
        HttpClientModule,
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
