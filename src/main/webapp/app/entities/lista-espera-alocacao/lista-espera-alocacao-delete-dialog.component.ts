import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ListaEsperaAlocacao } from './lista-espera-alocacao.model';
import { ListaEsperaAlocacaoPopupService } from './lista-espera-alocacao-popup.service';
import { ListaEsperaAlocacaoService } from './lista-espera-alocacao.service';

@Component({
    selector: 'jhi-lista-espera-alocacao-delete-dialog',
    templateUrl: './lista-espera-alocacao-delete-dialog.component.html'
})
export class ListaEsperaAlocacaoDeleteDialogComponent {

    listaEsperaAlocacao: ListaEsperaAlocacao;

    constructor(
        private listaEsperaAlocacaoService: ListaEsperaAlocacaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.listaEsperaAlocacaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'listaEsperaAlocacaoListModification',
                content: 'Deleted an listaEsperaAlocacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-lista-espera-alocacao-delete-popup',
    template: ''
})
export class ListaEsperaAlocacaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private listaEsperaAlocacaoPopupService: ListaEsperaAlocacaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.listaEsperaAlocacaoPopupService
                .open(ListaEsperaAlocacaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
