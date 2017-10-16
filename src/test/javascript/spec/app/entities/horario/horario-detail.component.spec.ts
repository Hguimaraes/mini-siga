/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { MinisigaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { HorarioDetailComponent } from '../../../../../../main/webapp/app/entities/horario/horario-detail.component';
import { HorarioService } from '../../../../../../main/webapp/app/entities/horario/horario.service';
import { Horario } from '../../../../../../main/webapp/app/entities/horario/horario.model';

describe('Component Tests', () => {

    describe('Horario Management Detail Component', () => {
        let comp: HorarioDetailComponent;
        let fixture: ComponentFixture<HorarioDetailComponent>;
        let service: HorarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MinisigaTestModule],
                declarations: [HorarioDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    HorarioService,
                    JhiEventManager
                ]
            }).overrideTemplate(HorarioDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(HorarioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HorarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Horario(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.horario).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
