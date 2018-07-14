import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlarm } from 'app/shared/model/alarm.model';
import { AlarmService } from './alarm.service';

@Component({
    selector: 'isd-alarm-delete-dialog',
    templateUrl: './alarm-delete-dialog.component.html'
})
export class AlarmDeleteDialogComponent {
    alarm: IAlarm;

    constructor(private alarmService: AlarmService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.alarmService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'alarmListModification',
                content: 'Deleted an alarm'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'isd-alarm-delete-popup',
    template: ''
})
export class AlarmDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ alarm }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AlarmDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.alarm = alarm;
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
