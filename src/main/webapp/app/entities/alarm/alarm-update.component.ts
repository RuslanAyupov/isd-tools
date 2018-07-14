import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAlarm } from 'app/shared/model/alarm.model';
import { AlarmService } from './alarm.service';

@Component({
    selector: 'isd-alarm-update',
    templateUrl: './alarm-update.component.html'
})
export class AlarmUpdateComponent implements OnInit {
    private _alarm: IAlarm;
    isSaving: boolean;

    constructor(private alarmService: AlarmService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ alarm }) => {
            this.alarm = alarm;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.alarm.id !== undefined) {
            this.subscribeToSaveResponse(this.alarmService.update(this.alarm));
        } else {
            this.subscribeToSaveResponse(this.alarmService.create(this.alarm));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAlarm>>) {
        result.subscribe((res: HttpResponse<IAlarm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get alarm() {
        return this._alarm;
    }

    set alarm(alarm: IAlarm) {
        this._alarm = alarm;
    }
}
