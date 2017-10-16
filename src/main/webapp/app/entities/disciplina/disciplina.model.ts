import { BaseEntity } from './../../shared';

export class Disciplina implements BaseEntity {
    constructor(
        public id?: number,
        public codigo?: string,
        public nome?: string,
        public qtdCreditos?: number,
        public cargaHoraria?: number,
        public turmas?: BaseEntity[],
    ) {
    }
}
