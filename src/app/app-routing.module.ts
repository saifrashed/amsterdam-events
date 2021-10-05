import {NgModule} from '@angular/core';
import {CanDeactivate, RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./components/mainpage/home/home.component";
import {Overview1Component} from "./components/mainpage/events/overview1/overview1.component";
import {Overview2Component} from "./components/mainpage/events/overview2/overview2.component";
import {Overview3Component} from "./components/mainpage/events/overview3/overview3.component"; // CLI imports router
import {Overview4Component} from './components/mainpage/events/overview4/overview4.component';
import {Detail4Component} from './components/mainpage/events/detail4/detail4.component';
import {Detail41Component} from './components/mainpage/events/detail41/detail41.component';
import {Detail4qpComponent} from './components/mainpage/events/detail4qp/detail4qp.component';
import {Overview4qpComponent} from './components/mainpage/events/overview4qp/overview4qp.component';

import {NotFoundComponent} from './components/mainpage/not-found/not-found.component';
import {CanDeactivateGuard} from "./services/can-deactivate-guard.service";

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'overview-one', component: Overview1Component},
    {path: 'overview-two', component: Overview2Component},
    {path: 'overview-three', component: Overview3Component},
    {
        path: 'overview-four',
        component: Overview4Component,
        children: [
            {
                path: ':id', // child route path
                component: Detail4Component, // child route component that the router renders
            }
        ]
    },
    {
        path: 'overview-four-one',
        component: Overview4Component,
        children: [
            {
                path: ':id', // child route path
                component: Detail41Component, // child route component that the router renders
                canDeactivate: [CanDeactivateGuard]
            }
        ]
    },
    {
        path: 'overview-four-qp',
        component: Overview4qpComponent,
        children: [
            {
                path: 'edit', // child route path
                component: Detail4qpComponent, // child route component that the router renders
                canDeactivate: [CanDeactivateGuard]
            }
        ]
    },
    {
        path: '**', pathMatch: 'full',
        component: NotFoundComponent
    },
]; // sets up routes constant where you define your routes

// configures NgModule imports and exports
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
