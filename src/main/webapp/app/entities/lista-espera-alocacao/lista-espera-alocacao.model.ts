import { BaseEntity } from './../../shared';

export class ListaEsperaAlocacao implements BaseEntity {
    constructor(
        public id?: number,
        public data?: any,
        public aluno?: BaseEntity,
        public turma?: BaseEntity,
    ) {
    }
}
