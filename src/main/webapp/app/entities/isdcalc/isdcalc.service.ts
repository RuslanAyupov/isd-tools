import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IIsdcalc } from 'app/shared/model/isdcalc.model';

type EntityResponseType = HttpResponse<IIsdcalc>;
type EntityArrayResponseType = HttpResponse<IIsdcalc[]>;

@Injectable({ providedIn: 'root' })
export class IsdcalcService {
    private resourceUrl = SERVER_API_URL + 'api/isdcalcs';

    constructor(private http: HttpClient) {}

    create(isdcalc: IIsdcalc): Observable<EntityResponseType> {
        return this.http.post<IIsdcalc>(this.resourceUrl, isdcalc, { observe: 'response' });
    }

    update(isdcalc: IIsdcalc): Observable<EntityResponseType> {
        return this.http.put<IIsdcalc>(this.resourceUrl, isdcalc, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IIsdcalc>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IIsdcalc[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
