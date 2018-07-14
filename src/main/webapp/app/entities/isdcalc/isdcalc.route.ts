import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Isdcalc } from 'app/shared/model/isdcalc.model';
import { IsdcalcService } from './isdcalc.service';
import { IsdcalcComponent } from './isdcalc.component';
import { IsdcalcDetailComponent } from './isdcalc-detail.component';
import { IsdcalcUpdateComponent } from './isdcalc-update.component';
import { IsdcalcDeletePopupComponent } from './isdcalc-delete-dialog.component';
import { IIsdcalc } from 'app/shared/model/isdcalc.model';

@Injectable({ providedIn: 'root' })
export class IsdcalcResolve implements Resolve<IIsdcalc> {
    constructor(private service: IsdcalcService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((isdcalc: HttpResponse<Isdcalc>) => isdcalc.body));
        }
        return of(new Isdcalc());
    }
}

export const isdcalcRoute: Routes = [
    {
        path: 'isdcalc',
        component: IsdcalcComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'isdtoolsApp.isdcalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'isdcalc/:id/view',
        component: IsdcalcDetailComponent,
        resolve: {
            isdcalc: IsdcalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'isdtoolsApp.isdcalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'isdcalc/new',
        component: IsdcalcUpdateComponent,
        resolve: {
            isdcalc: IsdcalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'isdtoolsApp.isdcalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'isdcalc/:id/edit',
        component: IsdcalcUpdateComponent,
        resolve: {
            isdcalc: IsdcalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'isdtoolsApp.isdcalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const isdcalcPopupRoute: Routes = [
    {
        path: 'isdcalc/:id/delete',
        component: IsdcalcDeletePopupComponent,
        resolve: {
            isdcalc: IsdcalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'isdtoolsApp.isdcalc.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
