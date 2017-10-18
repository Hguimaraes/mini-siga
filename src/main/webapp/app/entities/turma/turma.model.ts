import { BaseEntity } from './../../shared';

export class Turma implements BaseEntity {
    constructor(
        public id?: number,
        public maxInscritos?: number,
        public sala?: string,
        public professor?: BaseEntity,
        public notas?: BaseEntity[],
        public listaesperas?: BaseEntity[],
        public inscritos?: BaseEntity[],
        public disciplina?: BaseEntity,
        public horarios?: BaseEntity[],
    ) {
    }
}
