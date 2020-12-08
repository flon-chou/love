import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IShopPerformance } from 'app/shared/model/shop-performance.model';

type EntityResponseType = HttpResponse<IShopPerformance>;
type EntityArrayResponseType = HttpResponse<IShopPerformance[]>;

@Injectable({ providedIn: 'root' })
export class ShopPerformanceService {
  public resourceUrl = SERVER_API_URL + 'api/shop-performances';

  constructor(protected http: HttpClient) {}

  create(shopPerformance: IShopPerformance): Observable<EntityResponseType> {
    return this.http.post<IShopPerformance>(this.resourceUrl, shopPerformance, { observe: 'response' });
  }

  update(shopPerformance: IShopPerformance): Observable<EntityResponseType> {
    return this.http.put<IShopPerformance>(this.resourceUrl, shopPerformance, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IShopPerformance>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IShopPerformance[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
