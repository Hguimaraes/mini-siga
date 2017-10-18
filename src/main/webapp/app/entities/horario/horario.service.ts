import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { Horario } from './horario.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class HorarioService {

    private resourceUrl = 'api/horarios';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(horario: Horario): Observable<Horario> {
        const copy = this.convert(horario);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(horario: Horario): Observable<Horario> {
        const copy = this.convert(horario);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Horario> {
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
        entity.horaInicio = this.dateUtils
            .convertDateTimeFromServer(entity.horaInicio);
        entity.horaFim = this.dateUtils
            .convertDateTimeFromServer(entity.horaFim);
    }

    private convert(horario: Horario): Horario {
        const copy: Horario = Object.assign({}, horario);

        copy.horaInicio = this.dateUtils.toDate(horario.horaInicio);

        copy.horaFim = this.dateUtils.toDate(horario.horaFim);
        return copy;
    }
}
