import { Route } from '@angular/router';

import { IsdMetricsMonitoringComponent } from './metrics.component';

export const metricsRoute: Route = {
    path: 'isd-metrics',
    component: IsdMetricsMonitoringComponent,
    data: {
        pageTitle: 'metrics.title'
    }
};
