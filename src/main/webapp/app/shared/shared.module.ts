import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { IsdtoolsSharedLibsModule, IsdtoolsSharedCommonModule, IsdLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [IsdtoolsSharedLibsModule, IsdtoolsSharedCommonModule],
    declarations: [IsdLoginModalComponent, HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [IsdLoginModalComponent],
    exports: [IsdtoolsSharedCommonModule, IsdLoginModalComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IsdtoolsSharedModule {}
