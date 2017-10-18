import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Horario } from './horario.model';
import { HorarioPopupService } from './horario-popup.service';
import { HorarioService } from './horario.service';
import { Turma, TurmaService } from '../turma';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-horario-dialog',
    templateUrl: './horario-dialog.component.html'
})
export class HorarioDialogComponent implements OnInit {

    horario: Horario;
    isSaving: boolean;

    turmas: Turma[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private horarioService: HorarioService,
        private turmaService: TurmaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.turmaService.query()
            .subscribe((res: ResponseWrapper) => { this.turmas = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.horario.id !== undefined) {
            this.subscribeToSaveResponse(
                this.horarioService.update(this.horario));
        } else {
            this.subscribeToSaveResponse(
                this.horarioService.create(this.horario));
        }
    }

    private subscribeToSaveResponse(result: Observable<Horario>) {
        result.subscribe((res: Horario) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Horario) {
        this.eventManager.broadcast({ name: 'horarioListModification', content: 'OK'});
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
    selector: 'jhi-horario-popup',
    template: ''
})
export class HorarioPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private horarioPopupService: HorarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.horarioPopupService
                    .open(HorarioDialogComponent as Component, params['id']);
            } else {
                this.horarioPopupService
                    .open(HorarioDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
