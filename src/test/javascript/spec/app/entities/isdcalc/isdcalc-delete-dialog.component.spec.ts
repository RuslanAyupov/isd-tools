/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IsdtoolsTestModule } from '../../../test.module';
import { IsdcalcDeleteDialogComponent } from 'app/entities/isdcalc/isdcalc-delete-dialog.component';
import { IsdcalcService } from 'app/entities/isdcalc/isdcalc.service';

describe('Component Tests', () => {
    describe('Isdcalc Management Delete Component', () => {
        let comp: IsdcalcDeleteDialogComponent;
        let fixture: ComponentFixture<IsdcalcDeleteDialogComponent>;
        let service: IsdcalcService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [IsdtoolsTestModule],
                declarations: [IsdcalcDeleteDialogComponent]
            })
                .overrideTemplate(IsdcalcDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(IsdcalcDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IsdcalcService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
