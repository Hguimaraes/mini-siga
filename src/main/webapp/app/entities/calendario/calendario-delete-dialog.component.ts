import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Calendario } from './calendario.model';
import { CalendarioPopupService } from './calendario-popup.service';
import { CalendarioService } from './calendario.service';

@Component({
    selector: 'jhi-calendario-delete-dialog',
    templateUrl: './calendario-delete-dialog.component.html'
})
export class CalendarioDeleteDialogComponent {

    calendario: Calendario;

    constructor(
        private calendarioService: CalendarioService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.calendarioService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'calendarioListModification',
                content: 'Deleted an calendario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-calendario-delete-popup',
    template: ''
})
export class CalendarioDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private calendarioPopupService: CalendarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.calendarioPopupService
                .open(CalendarioDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
