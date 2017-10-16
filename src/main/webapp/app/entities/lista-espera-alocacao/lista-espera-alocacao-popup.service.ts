import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { ListaEsperaAlocacao } from './lista-espera-alocacao.model';
import { ListaEsperaAlocacaoService } from './lista-espera-alocacao.service';

@Injectable()
export class ListaEsperaAlocacaoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private listaEsperaAlocacaoService: ListaEsperaAlocacaoService

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
                this.listaEsperaAlocacaoService.find(id).subscribe((listaEsperaAlocacao) => {
                    listaEsperaAlocacao.data = this.datePipe
                        .transform(listaEsperaAlocacao.data, 'yyyy-MM-ddThh:mm');
                    this.ngbModalRef = this.listaEsperaAlocacaoModalRef(component, listaEsperaAlocacao);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.listaEsperaAlocacaoModalRef(component, new ListaEsperaAlocacao());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    listaEsperaAlocacaoModalRef(component: Component, listaEsperaAlocacao: ListaEsperaAlocacao): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.listaEsperaAlocacao = listaEsperaAlocacao;
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
