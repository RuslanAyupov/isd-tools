import { Route } from '@angular/router';

import { IsdDocsComponent } from './docs.component';

export const docsRoute: Route = {
    path: 'docs',
    component: IsdDocsComponent,
    data: {
        pageTitle: 'global.menu.admin.apidocs'
    }
};
