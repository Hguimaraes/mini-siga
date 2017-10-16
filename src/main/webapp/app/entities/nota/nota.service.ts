import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Nota } from './nota.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class NotaService {

    private resourceUrl = 'api/notas';

    constructor(private http: Http) { }

    create(nota: Nota): Observable<Nota> {
        const copy = this.convert(nota);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(nota: Nota): Observable<Nota> {
        const copy = this.convert(nota);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Nota> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(nota: Nota): Nota {
        const copy: Nota = Object.assign({}, nota);
        return copy;
    }
}
