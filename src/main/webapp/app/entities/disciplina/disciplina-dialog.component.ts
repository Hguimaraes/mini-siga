import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Disciplina } from './disciplina.model';
import { DisciplinaPopupService } from './disciplina-popup.service';
import { DisciplinaService } from './disciplina.service';

@Component({
    selector: 'jhi-disciplina-dialog',
    templateUrl: './disciplina-dialog.component.html'
})
export class DisciplinaDialogComponent implements OnInit {

    disciplina: Disciplina;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private disciplinaService: DisciplinaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.disciplina.id !== undefined) {
            this.subscribeToSaveResponse(
                this.disciplinaService.update(this.disciplina));
        } else {
            this.subscribeToSaveResponse(
                this.disciplinaService.create(this.disciplina));
        }
    }

    private subscribeToSaveResponse(result: Observable<Disciplina>) {
        result.subscribe((res: Disciplina) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Disciplina) {
        this.eventManager.broadcast({ name: 'disciplinaListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-disciplina-popup',
    template: ''
})
export class DisciplinaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private disciplinaPopupService: DisciplinaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.disciplinaPopupService
                    .open(DisciplinaDialogComponent as Component, params['id']);
            } else {
                this.disciplinaPopupService
                    .open(DisciplinaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
