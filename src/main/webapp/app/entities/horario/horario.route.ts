import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { HorarioComponent } from './horario.component';
import { HorarioDetailComponent } from './horario-detail.component';
import { HorarioPopupComponent } from './horario-dialog.component';
import { HorarioDeletePopupComponent } from './horario-delete-dialog.component';

export const horarioRoute: Routes = [
    {
        path: 'horario',
        component: HorarioComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.horario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'horario/:id',
        component: HorarioDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.horario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const horarioPopupRoute: Routes = [
    {
        path: 'horario-new',
        component: HorarioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.horario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'horario/:id/edit',
        component: HorarioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.horario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'horario/:id/delete',
        component: HorarioDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.horario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
