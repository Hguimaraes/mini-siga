import { BaseEntity, User } from './../../shared';

export class Aluno implements BaseEntity {
    constructor(
        public id?: number,
        public cpf?: string,
        public dre?: string,
        public user?: User,
        public aluno?: BaseEntity,
        public turmas?: BaseEntity[],
    ) {
    }
}
