import { BaseEntity } from './../../shared';

export class Horario implements BaseEntity {
    constructor(
        public id?: number,
        public dia?: any,
        public horaInicio?: any,
        public horaFim?: any,
        public descExtenso?: string,
        public turmas?: BaseEntity[],
    ) {
    }
}
