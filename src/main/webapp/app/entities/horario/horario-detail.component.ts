import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Horario } from './horario.model';
import { HorarioService } from './horario.service';

@Component({
    selector: 'jhi-horario-detail',
    templateUrl: './horario-detail.component.html'
})
export class HorarioDetailComponent implements OnInit, OnDestroy {

    horario: Horario;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private horarioService: HorarioService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInHorarios();
    }

    load(id) {
        this.horarioService.find(id).subscribe((horario) => {
            this.horario = horario;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInHorarios() {
        this.eventSubscriber = this.eventManager.subscribe(
            'horarioListModification',
            (response) => this.load(this.horario.id)
        );
    }
}
