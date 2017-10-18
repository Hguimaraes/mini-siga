import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MinisigaSharedModule } from '../../shared';
import {
    CalendarioService,
    CalendarioPopupService,
    CalendarioComponent,
    CalendarioDetailComponent,
    CalendarioDialogComponent,
    CalendarioPopupComponent,
    CalendarioDeletePopupComponent,
    CalendarioDeleteDialogComponent,
    calendarioRoute,
    calendarioPopupRoute,
} from './';

const ENTITY_STATES = [
    ...calendarioRoute,
    ...calendarioPopupRoute,
];

@NgModule({
    imports: [
        MinisigaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CalendarioComponent,
        CalendarioDetailComponent,
        CalendarioDialogComponent,
        CalendarioDeleteDialogComponent,
        CalendarioPopupComponent,
        CalendarioDeletePopupComponent,
    ],
    entryComponents: [
        CalendarioComponent,
        CalendarioDialogComponent,
        CalendarioPopupComponent,
        CalendarioDeleteDialogComponent,
        CalendarioDeletePopupComponent,
    ],
    providers: [
        CalendarioService,
        CalendarioPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MinisigaCalendarioModule {}
