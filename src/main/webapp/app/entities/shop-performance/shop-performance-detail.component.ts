import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShopPerformance } from 'app/shared/model/shop-performance.model';

@Component({
  selector: 'jhi-shop-performance-detail',
  templateUrl: './shop-performance-detail.component.html',
})
export class ShopPerformanceDetailComponent implements OnInit {
  shopPerformance: IShopPerformance | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shopPerformance }) => (this.shopPerformance = shopPerformance));
  }

  previousState(): void {
    window.history.back();
  }
}
