import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ListaEsperaAlocacaoComponent } from './lista-espera-alocacao.component';
import { ListaEsperaAlocacaoDetailComponent } from './lista-espera-alocacao-detail.component';
import { ListaEsperaAlocacaoPopupComponent } from './lista-espera-alocacao-dialog.component';
import { ListaEsperaAlocacaoDeletePopupComponent } from './lista-espera-alocacao-delete-dialog.component';

export const listaEsperaAlocacaoRoute: Routes = [
    {
        path: 'lista-espera-alocacao',
        component: ListaEsperaAlocacaoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.listaEsperaAlocacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'lista-espera-alocacao/:id',
        component: ListaEsperaAlocacaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.listaEsperaAlocacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const listaEsperaAlocacaoPopupRoute: Routes = [
    {
        path: 'lista-espera-alocacao-new',
        component: ListaEsperaAlocacaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.listaEsperaAlocacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'lista-espera-alocacao/:id/edit',
        component: ListaEsperaAlocacaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.listaEsperaAlocacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'lista-espera-alocacao/:id/delete',
        component: ListaEsperaAlocacaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'minisigaApp.listaEsperaAlocacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
