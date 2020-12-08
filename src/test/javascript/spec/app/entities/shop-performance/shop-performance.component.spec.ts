import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { LoveTestModule } from '../../../test.module';
import { ShopPerformanceComponent } from 'app/entities/shop-performance/shop-performance.component';
import { ShopPerformanceService } from 'app/entities/shop-performance/shop-performance.service';
import { ShopPerformance } from 'app/shared/model/shop-performance.model';

describe('Component Tests', () => {
  describe('ShopPerformance Management Component', () => {
    let comp: ShopPerformanceComponent;
    let fixture: ComponentFixture<ShopPerformanceComponent>;
    let service: ShopPerformanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LoveTestModule],
        declarations: [ShopPerformanceComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(ShopPerformanceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShopPerformanceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShopPerformanceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ShopPerformance(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.shopPerformances && comp.shopPerformances[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ShopPerformance(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.shopPerformances && comp.shopPerformances[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
