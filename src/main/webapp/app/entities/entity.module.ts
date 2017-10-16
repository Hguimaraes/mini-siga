import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MinisigaAlunoModule } from './aluno/aluno.module';
import { MinisigaFuncionarioModule } from './funcionario/funcionario.module';
import { MinisigaDisciplinaModule } from './disciplina/disciplina.module';
import { MinisigaTurmaModule } from './turma/turma.module';
import { MinisigaHorarioModule } from './horario/horario.module';
import { MinisigaListaEsperaAlocacaoModule } from './lista-espera-alocacao/lista-espera-alocacao.module';
import { MinisigaNotaModule } from './nota/nota.module';
import { MinisigaCalendarioModule } from './calendario/calendario.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        MinisigaAlunoModule,
        MinisigaFuncionarioModule,
        MinisigaDisciplinaModule,
        MinisigaTurmaModule,
        MinisigaHorarioModule,
        MinisigaListaEsperaAlocacaoModule,
        MinisigaNotaModule,
        MinisigaCalendarioModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MinisigaEntityModule {}
