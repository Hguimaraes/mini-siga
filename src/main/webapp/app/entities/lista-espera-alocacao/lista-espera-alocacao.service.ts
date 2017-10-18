import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { ListaEsperaAlocacao } from './lista-espera-alocacao.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ListaEsperaAlocacaoService {

    private resourceUrl = 'api/lista-espera-alocacaos';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(listaEsperaAlocacao: ListaEsperaAlocacao): Observable<ListaEsperaAlocacao> {
        const copy = this.convert(listaEsperaAlocacao);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(listaEsperaAlocacao: ListaEsperaAlocacao): Observable<ListaEsperaAlocacao> {
        const copy = this.convert(listaEsperaAlocacao);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<ListaEsperaAlocacao> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
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
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.data = this.dateUtils
            .convertDateTimeFromServer(entity.data);
    }

    private convert(listaEsperaAlocacao: ListaEsperaAlocacao): ListaEsperaAlocacao {
        const copy: ListaEsperaAlocacao = Object.assign({}, listaEsperaAlocacao);

        copy.data = this.dateUtils.toDate(listaEsperaAlocacao.data);
        return copy;
    }
}
