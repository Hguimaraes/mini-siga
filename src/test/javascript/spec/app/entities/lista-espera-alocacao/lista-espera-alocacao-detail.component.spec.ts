/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { MinisigaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ListaEsperaAlocacaoDetailComponent } from '../../../../../../main/webapp/app/entities/lista-espera-alocacao/lista-espera-alocacao-detail.component';
import { ListaEsperaAlocacaoService } from '../../../../../../main/webapp/app/entities/lista-espera-alocacao/lista-espera-alocacao.service';
import { ListaEsperaAlocacao } from '../../../../../../main/webapp/app/entities/lista-espera-alocacao/lista-espera-alocacao.model';

describe('Component Tests', () => {

    describe('ListaEsperaAlocacao Management Detail Component', () => {
        let comp: ListaEsperaAlocacaoDetailComponent;
        let fixture: ComponentFixture<ListaEsperaAlocacaoDetailComponent>;
        let service: ListaEsperaAlocacaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MinisigaTestModule],
                declarations: [ListaEsperaAlocacaoDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ListaEsperaAlocacaoService,
                    JhiEventManager
                ]
            }).overrideTemplate(ListaEsperaAlocacaoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ListaEsperaAlocacaoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ListaEsperaAlocacaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ListaEsperaAlocacao(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.listaEsperaAlocacao).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
