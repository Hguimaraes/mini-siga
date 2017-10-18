import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MinisigaSharedModule } from '../../shared';
import { MinisigaAdminModule } from '../../admin/admin.module';
import {
    AlunoService,
    AlunoPopupService,
    AlunoComponent,
    AlunoDetailComponent,
    AlunoDialogComponent,
    AlunoPopupComponent,
    AlunoDeletePopupComponent,
    AlunoDeleteDialogComponent,
    alunoRoute,
    alunoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...alunoRoute,
    ...alunoPopupRoute,
];

@NgModule({
    imports: [
        MinisigaSharedModule,
        MinisigaAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AlunoComponent,
        AlunoDetailComponent,
        AlunoDialogComponent,
        AlunoDeleteDialogComponent,
        AlunoPopupComponent,
        AlunoDeletePopupComponent,
    ],
    entryComponents: [
        AlunoComponent,
        AlunoDialogComponent,
        AlunoPopupComponent,
        AlunoDeleteDialogComponent,
        AlunoDeletePopupComponent,
    ],
    providers: [
        AlunoService,
        AlunoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MinisigaAlunoModule {}
