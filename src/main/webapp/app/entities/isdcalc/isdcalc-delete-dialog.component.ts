import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIsdcalc } from 'app/shared/model/isdcalc.model';
import { IsdcalcService } from './isdcalc.service';

@Component({
    selector: 'isd-isdcalc-delete-dialog',
    templateUrl: './isdcalc-delete-dialog.component.html'
})
export class IsdcalcDeleteDialogComponent {
    isdcalc: IIsdcalc;

    constructor(private isdcalcService: IsdcalcService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.isdcalcService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'isdcalcListModification',
                content: 'Deleted an isdcalc'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'isd-isdcalc-delete-popup',
    template: ''
})
export class IsdcalcDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ isdcalc }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(IsdcalcDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.isdcalc = isdcalc;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
