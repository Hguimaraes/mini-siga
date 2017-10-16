import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Nota } from './nota.model';
import { NotaPopupService } from './nota-popup.service';
import { NotaService } from './nota.service';
import { Aluno, AlunoService } from '../aluno';
import { Turma, TurmaService } from '../turma';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-nota-dialog',
    templateUrl: './nota-dialog.component.html'
})
export class NotaDialogComponent implements OnInit {

    nota: Nota;
    isSaving: boolean;

    alunos: Aluno[];

    turmas: Turma[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private notaService: NotaService,
        private alunoService: AlunoService,
        private turmaService: TurmaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.alunoService
            .query({filter: 'aluno-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.nota.aluno || !this.nota.aluno.id) {
                    this.alunos = res.json;
                } else {
                    this.alunoService
                        .find(this.nota.aluno.id)
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
        if (this.nota.id !== undefined) {
            this.subscribeToSaveResponse(
                this.notaService.update(this.nota));
        } else {
            this.subscribeToSaveResponse(
                this.notaService.create(this.nota));
        }
    }

    private subscribeToSaveResponse(result: Observable<Nota>) {
        result.subscribe((res: Nota) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Nota) {
        this.eventManager.broadcast({ name: 'notaListModification', content: 'OK'});
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
    selector: 'jhi-nota-popup',
    template: ''
})
export class NotaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private notaPopupService: NotaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.notaPopupService
                    .open(NotaDialogComponent as Component, params['id']);
            } else {
                this.notaPopupService
                    .open(NotaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
