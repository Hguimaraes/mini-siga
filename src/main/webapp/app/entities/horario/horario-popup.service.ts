import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Horario } from './horario.model';
import { HorarioService } from './horario.service';

@Injectable()
export class HorarioPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private horarioService: HorarioService

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
                this.horarioService.find(id).subscribe((horario) => {
                    horario.horaInicio = this.datePipe
                        .transform(horario.horaInicio, 'yyyy-MM-ddThh:mm');
                    horario.horaFim = this.datePipe
                        .transform(horario.horaFim, 'yyyy-MM-ddThh:mm');
                    this.ngbModalRef = this.horarioModalRef(component, horario);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.horarioModalRef(component, new Horario());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    horarioModalRef(component: Component, horario: Horario): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.horario = horario;
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
