import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IsdtoolsSharedModule } from 'app/shared';
import {
    IsdcalcComponent,
    IsdcalcDetailComponent,
    IsdcalcUpdateComponent,
    IsdcalcDeletePopupComponent,
    IsdcalcDeleteDialogComponent,
    isdcalcRoute,
    isdcalcPopupRoute
} from './';

const ENTITY_STATES = [...isdcalcRoute, ...isdcalcPopupRoute];

@NgModule({
    imports: [IsdtoolsSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        IsdcalcComponent,
        IsdcalcDetailComponent,
        IsdcalcUpdateComponent,
        IsdcalcDeleteDialogComponent,
        IsdcalcDeletePopupComponent
    ],
    entryComponents: [IsdcalcComponent, IsdcalcUpdateComponent, IsdcalcDeleteDialogComponent, IsdcalcDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IsdtoolsIsdcalcModule {}
