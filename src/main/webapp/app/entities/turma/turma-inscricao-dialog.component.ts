import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Turma } from './turma.model';
import { TurmaPopupService } from './turma-popup.service';
import { TurmaService } from './turma.service';

import { User, Principal } from '../../shared';

import { Aluno } from '../aluno/aluno.model';
import { AlunoService } from '../aluno/aluno.service';

@Component({
    selector: 'jhi-turma-inscricao-dialog',
    templateUrl: './turma-inscricao-dialog.component.html'
})
export class TurmaInscricaoDialogComponent implements OnInit {

    turma: Turma;
    user: User;
    aluno: Aluno;

    constructor(
        private turmaService: TurmaService,
        private principal: Principal,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager,
        private alunoService: AlunoService
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((user) => {
            this.user = user;
        });
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmRegister(id: number) {

        this.alunoService.findByUserId(this.user.id).subscribe(
            (val) => {
                this.aluno = val
                this.turma.inscritos.push(this.aluno);
                this.turmaService.update(this.turma).subscribe((response) => {
                    this.activeModal.dismiss(true);
                });
        });
    }
}

@Component({
    selector: 'jhi-turma-inscricao-popup',
    template: ''
})
export class TurmaInscricaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private turmaPopupService: TurmaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.turmaPopupService
                .open(TurmaInscricaoDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
