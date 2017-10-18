import { BaseEntity, User } from './../../shared';

export class Funcionario implements BaseEntity {
    constructor(
        public id?: number,
        public cpf?: string,
        public siap?: string,
        public user?: User,
    ) {
    }
}
