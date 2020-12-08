import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShopPerformance } from 'app/shared/model/shop-performance.model';
import { ShopPerformanceService } from './shop-performance.service';

@Component({
  templateUrl: './shop-performance-delete-dialog.component.html',
})
export class ShopPerformanceDeleteDialogComponent {
  shopPerformance?: IShopPerformance;

  constructor(
    protected shopPerformanceService: ShopPerformanceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.shopPerformanceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('shopPerformanceListModification');
      this.activeModal.close();
    });
  }
}
