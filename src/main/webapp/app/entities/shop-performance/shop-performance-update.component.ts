import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IShopPerformance, ShopPerformance } from 'app/shared/model/shop-performance.model';
import { ShopPerformanceService } from './shop-performance.service';

@Component({
  selector: 'jhi-shop-performance-update',
  templateUrl: './shop-performance-update.component.html',
})
export class ShopPerformanceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    shopName: [null, [Validators.maxLength(50)]],
    primeraConsulta: [null, [Validators.maxLength(64)]],
    primeraConsultaLeave: [null, [Validators.maxLength(128)]],
    nuevaConsulta: [null, [Validators.maxLength(64)]],
    todayPerformance: [null, [Validators.maxLength(1)]],
    createTime: [],
    del: [null, [Validators.max(1)]],
  });

  constructor(
    protected shopPerformanceService: ShopPerformanceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shopPerformance }) => {
      this.updateForm(shopPerformance);
    });
  }

  updateForm(shopPerformance: IShopPerformance): void {
    this.editForm.patchValue({
      id: shopPerformance.id,
      shopName: shopPerformance.shopName,
      primeraConsulta: shopPerformance.primeraConsulta,
      primeraConsultaLeave: shopPerformance.primeraConsultaLeave,
      nuevaConsulta: shopPerformance.nuevaConsulta,
      todayPerformance: shopPerformance.todayPerformance,
      createTime: shopPerformance.createTime,
      del: shopPerformance.del,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shopPerformance = this.createFromForm();
    if (shopPerformance.id !== undefined) {
      this.subscribeToSaveResponse(this.shopPerformanceService.update(shopPerformance));
    } else {
      this.subscribeToSaveResponse(this.shopPerformanceService.create(shopPerformance));
    }
  }

  private createFromForm(): IShopPerformance {
    return {
      ...new ShopPerformance(),
      id: this.editForm.get(['id'])!.value,
      shopName: this.editForm.get(['shopName'])!.value,
      primeraConsulta: this.editForm.get(['primeraConsulta'])!.value,
      primeraConsultaLeave: this.editForm.get(['primeraConsultaLeave'])!.value,
      nuevaConsulta: this.editForm.get(['nuevaConsulta'])!.value,
      todayPerformance: this.editForm.get(['todayPerformance'])!.value,
      createTime: this.editForm.get(['createTime'])!.value,
      del: this.editForm.get(['del'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShopPerformance>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
