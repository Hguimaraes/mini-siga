import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Calendario } from './calendario.model';
import { CalendarioPopupService } from './calendario-popup.service';
import { CalendarioService } from './calendario.service';

@Component({
    selector: 'jhi-calendario-dialog',
    templateUrl: './calendario-dialog.component.html'
})
export class CalendarioDialogComponent implements OnInit {

    calendario: Calendario;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private calendarioService: CalendarioService,
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
        if (this.calendario.id !== undefined) {
            this.subscribeToSaveResponse(
                this.calendarioService.update(this.calendario));
        } else {
            this.subscribeToSaveResponse(
                this.calendarioService.create(this.calendario));
        }
    }

    private subscribeToSaveResponse(result: Observable<Calendario>) {
        result.subscribe((res: Calendario) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Calendario) {
        this.eventManager.broadcast({ name: 'calendarioListModification', content: 'OK'});
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
    selector: 'jhi-calendario-popup',
    template: ''
})
export class CalendarioPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private calendarioPopupService: CalendarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.calendarioPopupService
                    .open(CalendarioDialogComponent as Component, params['id']);
            } else {
                this.calendarioPopupService
                    .open(CalendarioDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
