import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { Horario } from './horario.model';
import { HorarioService } from './horario.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-horario',
    templateUrl: './horario.component.html'
})
export class HorarioComponent implements OnInit, OnDestroy {
horarios: Horario[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private horarioService: HorarioService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.horarioService.query().subscribe(
            (res: ResponseWrapper) => {
                this.horarios = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInHorarios();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Horario) {
        return item.id;
    }
    registerChangeInHorarios() {
        this.eventSubscriber = this.eventManager.subscribe('horarioListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
