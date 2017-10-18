import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CalendarioComponent } from './calendario.component';
import { CalendarioDetailComponent } from './calendario-detail.component';
import { CalendarioPopupComponent } from './calendario-dialog.component';
import { CalendarioDeletePopupComponent } from './calendario-delete-dialog.component';

export const calendarioRoute: Routes = [
    {
        path: 'calendario',
        component: CalendarioComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.calendario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'calendario/:id',
        component: CalendarioDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.calendario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const calendarioPopupRoute: Routes = [
    {
        path: 'calendario-new',
        component: CalendarioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.calendario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'calendario/:id/edit',
        component: CalendarioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.calendario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'calendario/:id/delete',
        component: CalendarioDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.calendario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
