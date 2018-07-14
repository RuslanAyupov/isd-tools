import { Route } from '@angular/router';

import { IsdHealthCheckComponent } from './health.component';

export const healthRoute: Route = {
    path: 'isd-health',
    component: IsdHealthCheckComponent,
    data: {
        pageTitle: 'health.title'
    }
};
