import { BaseEntity } from './../../shared';

export class Calendario implements BaseEntity {
    constructor(
        public id?: number,
        public periodo?: string,
        public iniPeriodo?: any,
        public fimPeriodo?: any,
        public iniInscDisc?: any,
        public fimInsDisc?: any,
        public iniTrancDisc?: any,
        public fimTrancDisc?: any,
    ) {
    }
}
