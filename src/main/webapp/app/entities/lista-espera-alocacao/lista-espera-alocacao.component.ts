import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { ListaEsperaAlocacao } from './lista-espera-alocacao.model';
import { ListaEsperaAlocacaoService } from './lista-espera-alocacao.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-lista-espera-alocacao',
    templateUrl: './lista-espera-alocacao.component.html'
})
export class ListaEsperaAlocacaoComponent implements OnInit, OnDestroy {
listaEsperaAlocacaos: ListaEsperaAlocacao[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private listaEsperaAlocacaoService: ListaEsperaAlocacaoService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.listaEsperaAlocacaoService.query().subscribe(
            (res: ResponseWrapper) => {
                this.listaEsperaAlocacaos = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInListaEsperaAlocacaos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ListaEsperaAlocacao) {
        return item.id;
    }
    registerChangeInListaEsperaAlocacaos() {
        this.eventSubscriber = this.eventManager.subscribe('listaEsperaAlocacaoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
