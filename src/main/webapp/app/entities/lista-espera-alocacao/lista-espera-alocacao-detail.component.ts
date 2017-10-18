import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { ListaEsperaAlocacao } from './lista-espera-alocacao.model';
import { ListaEsperaAlocacaoService } from './lista-espera-alocacao.service';

@Component({
    selector: 'jhi-lista-espera-alocacao-detail',
    templateUrl: './lista-espera-alocacao-detail.component.html'
})
export class ListaEsperaAlocacaoDetailComponent implements OnInit, OnDestroy {

    listaEsperaAlocacao: ListaEsperaAlocacao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private listaEsperaAlocacaoService: ListaEsperaAlocacaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInListaEsperaAlocacaos();
    }

    load(id) {
        this.listaEsperaAlocacaoService.find(id).subscribe((listaEsperaAlocacao) => {
            this.listaEsperaAlocacao = listaEsperaAlocacao;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInListaEsperaAlocacaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'listaEsperaAlocacaoListModification',
            (response) => this.load(this.listaEsperaAlocacao.id)
        );
    }
}
