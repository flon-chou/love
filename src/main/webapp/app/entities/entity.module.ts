import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'shop-performance',
        loadChildren: () => import('./shop-performance/shop-performance.module').then(m => m.LoveShopPerformanceModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class LoveEntityModule {}
