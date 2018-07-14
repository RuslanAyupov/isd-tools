import './vendor.ts';

import { NgModule, Injector } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Ng2Webstorage } from 'ngx-webstorage';
import { JhiEventManager } from 'ng-jhipster';

import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { IsdtoolsSharedModule } from 'app/shared';
import { IsdtoolsCoreModule } from 'app/core';
import { IsdtoolsAppRoutingModule } from './app-routing.module';
import { IsdtoolsHomeModule } from './home/home.module';
import { IsdtoolsAccountModule } from './account/account.module';
import { IsdtoolsEntityModule } from './entities/entity.module';
import { StateStorageService } from 'app/core/auth/state-storage.service';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { IsdMainComponent, NavbarComponent, FooterComponent, PageRibbonComponent, ActiveMenuDirective, ErrorComponent } from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        IsdtoolsAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'isd', separator: '-' }),
        IsdtoolsSharedModule,
        IsdtoolsCoreModule,
        IsdtoolsHomeModule,
        IsdtoolsAccountModule,
        IsdtoolsEntityModule
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [IsdMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [StateStorageService, Injector]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [JhiEventManager]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [Injector]
        }
    ],
    bootstrap: [IsdMainComponent]
})
export class IsdtoolsAppModule {}
