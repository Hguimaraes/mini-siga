import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Horario } from './horario.model';
import { HorarioPopupService } from './horario-popup.service';
import { HorarioService } from './horario.service';

@Component({
    selector: 'jhi-horario-delete-dialog',
    templateUrl: './horario-delete-dialog.component.html'
})
export class HorarioDeleteDialogComponent {

    horario: Horario;

    constructor(
        private horarioService: HorarioService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.horarioService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'horarioListModification',
                content: 'Deleted an horario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-horario-delete-popup',
    template: ''
})
export class HorarioDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private horarioPopupService: HorarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.horarioPopupService
                .open(HorarioDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
