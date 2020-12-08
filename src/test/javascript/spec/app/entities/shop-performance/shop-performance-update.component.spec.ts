import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LoveTestModule } from '../../../test.module';
import { ShopPerformanceUpdateComponent } from 'app/entities/shop-performance/shop-performance-update.component';
import { ShopPerformanceService } from 'app/entities/shop-performance/shop-performance.service';
import { ShopPerformance } from 'app/shared/model/shop-performance.model';

describe('Component Tests', () => {
  describe('ShopPerformance Management Update Component', () => {
    let comp: ShopPerformanceUpdateComponent;
    let fixture: ComponentFixture<ShopPerformanceUpdateComponent>;
    let service: ShopPerformanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LoveTestModule],
        declarations: [ShopPerformanceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ShopPerformanceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShopPerformanceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShopPerformanceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShopPerformance(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShopPerformance();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
