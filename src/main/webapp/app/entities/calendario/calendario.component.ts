import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { Calendario } from './calendario.model';
import { CalendarioService } from './calendario.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-calendario',
    templateUrl: './calendario.component.html'
})
export class CalendarioComponent implements OnInit, OnDestroy {
calendarios: Calendario[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private calendarioService: CalendarioService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.calendarioService.query().subscribe(
            (res: ResponseWrapper) => {
                this.calendarios = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCalendarios();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Calendario) {
        return item.id;
    }
    registerChangeInCalendarios() {
        this.eventSubscriber = this.eventManager.subscribe('calendarioListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
