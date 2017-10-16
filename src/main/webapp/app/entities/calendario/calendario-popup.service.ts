import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Calendario } from './calendario.model';
import { CalendarioService } from './calendario.service';

@Injectable()
export class CalendarioPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private calendarioService: CalendarioService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.calendarioService.find(id).subscribe((calendario) => {
                    calendario.iniPeriodo = this.datePipe
                        .transform(calendario.iniPeriodo, 'yyyy-MM-ddThh:mm');
                    calendario.fimPeriodo = this.datePipe
                        .transform(calendario.fimPeriodo, 'yyyy-MM-ddThh:mm');
                    calendario.iniInscDisc = this.datePipe
                        .transform(calendario.iniInscDisc, 'yyyy-MM-ddThh:mm');
                    calendario.fimInsDisc = this.datePipe
                        .transform(calendario.fimInsDisc, 'yyyy-MM-ddThh:mm');
                    calendario.iniTrancDisc = this.datePipe
                        .transform(calendario.iniTrancDisc, 'yyyy-MM-ddThh:mm');
                    calendario.fimTrancDisc = this.datePipe
                        .transform(calendario.fimTrancDisc, 'yyyy-MM-ddThh:mm');
                    this.ngbModalRef = this.calendarioModalRef(component, calendario);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.calendarioModalRef(component, new Calendario());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    calendarioModalRef(component: Component, calendario: Calendario): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.calendario = calendario;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
