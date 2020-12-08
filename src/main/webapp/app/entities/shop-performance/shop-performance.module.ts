import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LoveSharedModule } from 'app/shared/shared.module';
import { ShopPerformanceComponent } from './shop-performance.component';
import { ShopPerformanceDetailComponent } from './shop-performance-detail.component';
import { ShopPerformanceUpdateComponent } from './shop-performance-update.component';
import { ShopPerformanceDeleteDialogComponent } from './shop-performance-delete-dialog.component';
import { shopPerformanceRoute } from './shop-performance.route';

@NgModule({
  imports: [LoveSharedModule, RouterModule.forChild(shopPerformanceRoute)],
  declarations: [
    ShopPerformanceComponent,
    ShopPerformanceDetailComponent,
    ShopPerformanceUpdateComponent,
    ShopPerformanceDeleteDialogComponent,
  ],
  entryComponents: [ShopPerformanceDeleteDialogComponent],
})
export class LoveShopPerformanceModule {}
