import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IsdtoolsSharedModule } from 'app/shared';
import {
    AlarmComponent,
    AlarmDetailComponent,
    AlarmUpdateComponent,
    AlarmDeletePopupComponent,
    AlarmDeleteDialogComponent,
    alarmRoute,
    alarmPopupRoute
} from './';

const ENTITY_STATES = [...alarmRoute, ...alarmPopupRoute];

@NgModule({
    imports: [IsdtoolsSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [AlarmComponent, AlarmDetailComponent, AlarmUpdateComponent, AlarmDeleteDialogComponent, AlarmDeletePopupComponent],
    entryComponents: [AlarmComponent, AlarmUpdateComponent, AlarmDeleteDialogComponent, AlarmDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IsdtoolsAlarmModule {}
