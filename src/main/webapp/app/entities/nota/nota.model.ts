import { BaseEntity } from './../../shared';

const enum CONCEITO {
    'A',
    'B',
    'C',
    'D'
}

export class Nota implements BaseEntity {
    constructor(
        public id?: number,
        public conceito?: CONCEITO,
        public aluno?: BaseEntity,
        public turma?: BaseEntity,
    ) {
    }
}
