import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ListaEsperaAlocacao } from './lista-espera-alocacao.model';
import { ListaEsperaAlocacaoPopupService } from './lista-espera-alocacao-popup.service';
import { ListaEsperaAlocacaoService } from './lista-espera-alocacao.service';
import { Aluno, AlunoService } from '../aluno';
import { Turma, TurmaService } from '../turma';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-lista-espera-alocacao-dialog',
    templateUrl: './lista-espera-alocacao-dialog.component.html'
})
export class ListaEsperaAlocacaoDialogComponent implements OnInit {

    listaEsperaAlocacao: ListaEsperaAlocacao;
    isSaving: boolean;

    alunos: Aluno[];

    turmas: Turma[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private listaEsperaAlocacaoService: ListaEsperaAlocacaoService,
        private alunoService: AlunoService,
        private turmaService: TurmaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.alunoService
            .query({filter: 'listaesperaalocacao-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.listaEsperaAlocacao.aluno || !this.listaEsperaAlocacao.aluno.id) {
                    this.alunos = res.json;
                } else {
                    this.alunoService
                        .find(this.listaEsperaAlocacao.aluno.id)
                        .subscribe((subRes: Aluno) => {
                            this.alunos = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.turmaService.query()
            .subscribe((res: ResponseWrapper) => { this.turmas = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.listaEsperaAlocacao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.listaEsperaAlocacaoService.update(this.listaEsperaAlocacao));
        } else {
            this.subscribeToSaveResponse(
                this.listaEsperaAlocacaoService.create(this.listaEsperaAlocacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<ListaEsperaAlocacao>) {
        result.subscribe((res: ListaEsperaAlocacao) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ListaEsperaAlocacao) {
        this.eventManager.broadcast({ name: 'listaEsperaAlocacaoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackAlunoById(index: number, item: Aluno) {
        return item.id;
    }

    trackTurmaById(index: number, item: Turma) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-lista-espera-alocacao-popup',
    template: ''
})
export class ListaEsperaAlocacaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private listaEsperaAlocacaoPopupService: ListaEsperaAlocacaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.listaEsperaAlocacaoPopupService
                    .open(ListaEsperaAlocacaoDialogComponent as Component, params['id']);
            } else {
                this.listaEsperaAlocacaoPopupService
                    .open(ListaEsperaAlocacaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
