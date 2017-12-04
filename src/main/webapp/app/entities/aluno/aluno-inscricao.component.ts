import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Aluno } from './aluno.model';
import { AlunoService } from './aluno.service';

import { Turma } from '../turma/turma.model';

@Component({
    selector: 'jhi-aluno-inscricao',
    templateUrl: './aluno-inscricao.component.html'
})
export class AlunoInscricaoComponent implements OnInit {

    aluno: Aluno;
    turmas: Turma[];

    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private alunoService: AlunoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        // subscribe to router event
        this.route.params.subscribe((params: Params) => {
            const id = params['id'];
            this.alunoService.findByUserId(id).subscribe((val) => {
                this.aluno = val
                console.log(this.aluno);
                this.alunoService.getAllTurmas(this.aluno.id).subscribe(
                    (t) => {this.turmas = t; console.log(t);}
                )
            });
        });
    }
}
