import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IIsdcalc } from 'app/shared/model/isdcalc.model';
import { IsdcalcService } from './isdcalc.service';

@Component({
    selector: 'isd-isdcalc-update',
    templateUrl: './isdcalc-update.component.html'
})
export class IsdcalcUpdateComponent implements OnInit {
    private _isdcalc: IIsdcalc;
    isSaving: boolean;

    constructor(private isdcalcService: IsdcalcService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ isdcalc }) => {
            this.isdcalc = isdcalc;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.isdcalc.id !== undefined) {
            this.subscribeToSaveResponse(this.isdcalcService.update(this.isdcalc));
        } else {
            this.subscribeToSaveResponse(this.isdcalcService.create(this.isdcalc));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IIsdcalc>>) {
        result.subscribe((res: HttpResponse<IIsdcalc>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get isdcalc() {
        return this._isdcalc;
    }

    set isdcalc(isdcalc: IIsdcalc) {
        this._isdcalc = isdcalc;
    }
}
