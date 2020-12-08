import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LoveTestModule } from '../../../test.module';
import { ShopPerformanceDetailComponent } from 'app/entities/shop-performance/shop-performance-detail.component';
import { ShopPerformance } from 'app/shared/model/shop-performance.model';

describe('Component Tests', () => {
  describe('ShopPerformance Management Detail Component', () => {
    let comp: ShopPerformanceDetailComponent;
    let fixture: ComponentFixture<ShopPerformanceDetailComponent>;
    const route = ({ data: of({ shopPerformance: new ShopPerformance(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LoveTestModule],
        declarations: [ShopPerformanceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ShopPerformanceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShopPerformanceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load shopPerformance on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.shopPerformance).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
