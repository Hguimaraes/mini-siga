import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Aluno } from './aluno.model';
import { AlunoPopupService } from './aluno-popup.service';
import { AlunoService } from './aluno.service';
import { User, UserService } from '../../shared';
import { Nota, NotaService } from '../nota';
import { Turma, TurmaService } from '../turma';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-aluno-dialog',
    templateUrl: './aluno-dialog.component.html'
})
export class AlunoDialogComponent implements OnInit {

    aluno: Aluno;
    isSaving: boolean;

    users: User[];

    notas: Nota[];

    turmas: Turma[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private alunoService: AlunoService,
        private userService: UserService,
        private notaService: NotaService,
        private turmaService: TurmaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.notaService.query()
            .subscribe((res: ResponseWrapper) => { this.notas = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.turmaService.query()
            .subscribe((res: ResponseWrapper) => { this.turmas = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.aluno.id !== undefined) {
            this.subscribeToSaveResponse(
                this.alunoService.update(this.aluno));
        } else {
            this.subscribeToSaveResponse(
                this.alunoService.create(this.aluno));
        }
    }

    private subscribeToSaveResponse(result: Observable<Aluno>) {
        result.subscribe((res: Aluno) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Aluno) {
        this.eventManager.broadcast({ name: 'alunoListModification', content: 'OK'});
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

    trackUserById(index: number, item: User) {
        return item.id;
    }

    trackNotaById(index: number, item: Nota) {
        return item.id;
    }

    trackTurmaById(index: number, item: Turma) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-aluno-popup',
    template: ''
})
export class AlunoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private alunoPopupService: AlunoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.alunoPopupService
                    .open(AlunoDialogComponent as Component, params['id']);
            } else {
                this.alunoPopupService
                    .open(AlunoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
