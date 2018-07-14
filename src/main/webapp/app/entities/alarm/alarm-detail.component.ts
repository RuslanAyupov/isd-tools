import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlarm } from 'app/shared/model/alarm.model';

@Component({
    selector: 'isd-alarm-detail',
    templateUrl: './alarm-detail.component.html'
})
export class AlarmDetailComponent implements OnInit {
    alarm: IAlarm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ alarm }) => {
            this.alarm = alarm;
        });
    }

    previousState() {
        window.history.back();
    }
}
