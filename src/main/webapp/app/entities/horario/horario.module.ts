import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MinisigaSharedModule } from '../../shared';
import {
    HorarioService,
    HorarioPopupService,
    HorarioComponent,
    HorarioDetailComponent,
    HorarioDialogComponent,
    HorarioPopupComponent,
    HorarioDeletePopupComponent,
    HorarioDeleteDialogComponent,
    horarioRoute,
    horarioPopupRoute,
} from './';

const ENTITY_STATES = [
    ...horarioRoute,
    ...horarioPopupRoute,
];

@NgModule({
    imports: [
        MinisigaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        HorarioComponent,
        HorarioDetailComponent,
        HorarioDialogComponent,
        HorarioDeleteDialogComponent,
        HorarioPopupComponent,
        HorarioDeletePopupComponent,
    ],
    entryComponents: [
        HorarioComponent,
        HorarioDialogComponent,
        HorarioPopupComponent,
        HorarioDeleteDialogComponent,
        HorarioDeletePopupComponent,
    ],
    providers: [
        HorarioService,
        HorarioPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MinisigaHorarioModule {}
