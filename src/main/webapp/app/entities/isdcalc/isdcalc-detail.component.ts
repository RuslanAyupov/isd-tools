import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIsdcalc } from 'app/shared/model/isdcalc.model';

@Component({
    selector: 'isd-isdcalc-detail',
    templateUrl: './isdcalc-detail.component.html'
})
export class IsdcalcDetailComponent implements OnInit {
    isdcalc: IIsdcalc;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ isdcalc }) => {
            this.isdcalc = isdcalc;
        });
    }

    previousState() {
        window.history.back();
    }
}
