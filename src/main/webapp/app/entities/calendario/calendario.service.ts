import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { Calendario } from './calendario.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CalendarioService {

    private resourceUrl = 'api/calendarios';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(calendario: Calendario): Observable<Calendario> {
        const copy = this.convert(calendario);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(calendario: Calendario): Observable<Calendario> {
        const copy = this.convert(calendario);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Calendario> {
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
        entity.iniPeriodo = this.dateUtils
            .convertDateTimeFromServer(entity.iniPeriodo);
        entity.fimPeriodo = this.dateUtils
            .convertDateTimeFromServer(entity.fimPeriodo);
        entity.iniInscDisc = this.dateUtils
            .convertDateTimeFromServer(entity.iniInscDisc);
        entity.fimInsDisc = this.dateUtils
            .convertDateTimeFromServer(entity.fimInsDisc);
        entity.iniTrancDisc = this.dateUtils
            .convertDateTimeFromServer(entity.iniTrancDisc);
        entity.fimTrancDisc = this.dateUtils
            .convertDateTimeFromServer(entity.fimTrancDisc);
    }

    private convert(calendario: Calendario): Calendario {
        const copy: Calendario = Object.assign({}, calendario);

        copy.iniPeriodo = this.dateUtils.toDate(calendario.iniPeriodo);

        copy.fimPeriodo = this.dateUtils.toDate(calendario.fimPeriodo);

        copy.iniInscDisc = this.dateUtils.toDate(calendario.iniInscDisc);

        copy.fimInsDisc = this.dateUtils.toDate(calendario.fimInsDisc);

        copy.iniTrancDisc = this.dateUtils.toDate(calendario.iniTrancDisc);

        copy.fimTrancDisc = this.dateUtils.toDate(calendario.fimTrancDisc);
        return copy;
    }
}
