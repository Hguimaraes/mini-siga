import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Turma } from './turma.model';
import { TurmaPopupService } from './turma-popup.service';
import { TurmaService } from './turma.service';
import { Funcionario, FuncionarioService } from '../funcionario';
import { Aluno, AlunoService } from '../aluno';
import { Disciplina, DisciplinaService } from '../disciplina';
import { Horario, HorarioService } from '../horario';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-turma-dialog',
    templateUrl: './turma-dialog.component.html'
})
export class TurmaDialogComponent implements OnInit {

    turma: Turma;
    isSaving: boolean;

    professors: Funcionario[];

    alunos: Aluno[];

    disciplinas: Disciplina[];

    horarios: Horario[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private turmaService: TurmaService,
        private funcionarioService: FuncionarioService,
        private alunoService: AlunoService,
        private disciplinaService: DisciplinaService,
        private horarioService: HorarioService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.funcionarioService
            .query({filter: 'turma-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.turma.professor || !this.turma.professor.id) {
                    this.professors = res.json;
                } else {
                    this.funcionarioService
                        .find(this.turma.professor.id)
                        .subscribe((subRes: Funcionario) => {
                            this.professors = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.alunoService.query()
            .subscribe((res: ResponseWrapper) => { this.alunos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.disciplinaService.query()
            .subscribe((res: ResponseWrapper) => { this.disciplinas = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.horarioService.query()
            .subscribe((res: ResponseWrapper) => { this.horarios = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.turma.id !== undefined) {
            this.subscribeToSaveResponse(
                this.turmaService.update(this.turma));
        } else {
            this.subscribeToSaveResponse(
                this.turmaService.create(this.turma));
        }
    }

    private subscribeToSaveResponse(result: Observable<Turma>) {
        result.subscribe((res: Turma) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Turma) {
        this.eventManager.broadcast({ name: 'turmaListModification', content: 'OK'});
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

    trackFuncionarioById(index: number, item: Funcionario) {
        return item.id;
    }

    trackAlunoById(index: number, item: Aluno) {
        return item.id;
    }

    trackDisciplinaById(index: number, item: Disciplina) {
        return item.id;
    }

    trackHorarioById(index: number, item: Horario) {
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
    selector: 'jhi-turma-popup',
    template: ''
})
export class TurmaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private turmaPopupService: TurmaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.turmaPopupService
                    .open(TurmaDialogComponent as Component, params['id']);
            } else {
                this.turmaPopupService
                    .open(TurmaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
