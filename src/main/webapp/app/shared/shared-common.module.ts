import { NgModule } from '@angular/core';

import { IsdtoolsSharedLibsModule, FindLanguageFromKeyPipe, IsdAlertComponent, IsdAlertErrorComponent } from './';

@NgModule({
    imports: [IsdtoolsSharedLibsModule],
    declarations: [FindLanguageFromKeyPipe, IsdAlertComponent, IsdAlertErrorComponent],
    exports: [IsdtoolsSharedLibsModule, FindLanguageFromKeyPipe, IsdAlertComponent, IsdAlertErrorComponent]
})
export class IsdtoolsSharedCommonModule {}
