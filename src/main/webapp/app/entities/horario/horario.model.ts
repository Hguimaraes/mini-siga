import { BaseEntity } from './../../shared';

const enum DIAS {
    'SEG',
    'TER',
    'QUA',
    'QUI',
    'SEX',
    'SAB'
}

export class Horario implements BaseEntity {
    constructor(
        public id?: number,
        public dia?: DIAS,
        public horaInicio?: any,
        public horaFim?: any,
        public descExtenso?: string,
        public turmas?: BaseEntity[],
    ) {
    }
}
