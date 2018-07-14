import { Route } from '@angular/router';

import { IsdConfigurationComponent } from './configuration.component';

export const configurationRoute: Route = {
    path: 'isd-configuration',
    component: IsdConfigurationComponent,
    data: {
        pageTitle: 'configuration.title'
    }
};
