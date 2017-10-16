import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MinisigaSharedModule } from '../../shared';
import {
    ListaEsperaAlocacaoService,
    ListaEsperaAlocacaoPopupService,
    ListaEsperaAlocacaoComponent,
    ListaEsperaAlocacaoDetailComponent,
    ListaEsperaAlocacaoDialogComponent,
    ListaEsperaAlocacaoPopupComponent,
    ListaEsperaAlocacaoDeletePopupComponent,
    ListaEsperaAlocacaoDeleteDialogComponent,
    listaEsperaAlocacaoRoute,
    listaEsperaAlocacaoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...listaEsperaAlocacaoRoute,
    ...listaEsperaAlocacaoPopupRoute,
];

@NgModule({
    imports: [
        MinisigaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ListaEsperaAlocacaoComponent,
        ListaEsperaAlocacaoDetailComponent,
        ListaEsperaAlocacaoDialogComponent,
        ListaEsperaAlocacaoDeleteDialogComponent,
        ListaEsperaAlocacaoPopupComponent,
        ListaEsperaAlocacaoDeletePopupComponent,
    ],
    entryComponents: [
        ListaEsperaAlocacaoComponent,
        ListaEsperaAlocacaoDialogComponent,
        ListaEsperaAlocacaoPopupComponent,
        ListaEsperaAlocacaoDeleteDialogComponent,
        ListaEsperaAlocacaoDeletePopupComponent,
    ],
    providers: [
        ListaEsperaAlocacaoService,
        ListaEsperaAlocacaoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MinisigaListaEsperaAlocacaoModule {}
