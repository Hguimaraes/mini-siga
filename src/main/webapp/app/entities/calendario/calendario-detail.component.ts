import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Calendario } from './calendario.model';
import { CalendarioService } from './calendario.service';

@Component({
    selector: 'jhi-calendario-detail',
    templateUrl: './calendario-detail.component.html'
})
export class CalendarioDetailComponent implements OnInit, OnDestroy {

    calendario: Calendario;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private calendarioService: CalendarioService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCalendarios();
    }

    load(id) {
        this.calendarioService.find(id).subscribe((calendario) => {
            this.calendario = calendario;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCalendarios() {
        this.eventSubscriber = this.eventManager.subscribe(
            'calendarioListModification',
            (response) => this.load(this.calendario.id)
        );
    }
}
