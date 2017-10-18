/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { MinisigaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CalendarioDetailComponent } from '../../../../../../main/webapp/app/entities/calendario/calendario-detail.component';
import { CalendarioService } from '../../../../../../main/webapp/app/entities/calendario/calendario.service';
import { Calendario } from '../../../../../../main/webapp/app/entities/calendario/calendario.model';

describe('Component Tests', () => {

    describe('Calendario Management Detail Component', () => {
        let comp: CalendarioDetailComponent;
        let fixture: ComponentFixture<CalendarioDetailComponent>;
        let service: CalendarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MinisigaTestModule],
                declarations: [CalendarioDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CalendarioService,
                    JhiEventManager
                ]
            }).overrideTemplate(CalendarioDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CalendarioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CalendarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Calendario(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.calendario).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
