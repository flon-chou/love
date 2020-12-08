import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IShopPerformance, ShopPerformance } from 'app/shared/model/shop-performance.model';
import { ShopPerformanceService } from './shop-performance.service';
import { ShopPerformanceComponent } from './shop-performance.component';
import { ShopPerformanceDetailComponent } from './shop-performance-detail.component';
import { ShopPerformanceUpdateComponent } from './shop-performance-update.component';

@Injectable({ providedIn: 'root' })
export class ShopPerformanceResolve implements Resolve<IShopPerformance> {
  constructor(private service: ShopPerformanceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IShopPerformance> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((shopPerformance: HttpResponse<ShopPerformance>) => {
          if (shopPerformance.body) {
            return of(shopPerformance.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ShopPerformance());
  }
}

export const shopPerformanceRoute: Routes = [
  {
    path: '',
    component: ShopPerformanceComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'loveApp.shopPerformance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ShopPerformanceDetailComponent,
    resolve: {
      shopPerformance: ShopPerformanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'loveApp.shopPerformance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ShopPerformanceUpdateComponent,
    resolve: {
      shopPerformance: ShopPerformanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'loveApp.shopPerformance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ShopPerformanceUpdateComponent,
    resolve: {
      shopPerformance: ShopPerformanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'loveApp.shopPerformance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
