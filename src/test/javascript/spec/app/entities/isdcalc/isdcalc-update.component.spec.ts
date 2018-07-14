/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { IsdtoolsTestModule } from '../../../test.module';
import { IsdcalcUpdateComponent } from 'app/entities/isdcalc/isdcalc-update.component';
import { IsdcalcService } from 'app/entities/isdcalc/isdcalc.service';
import { Isdcalc } from 'app/shared/model/isdcalc.model';

describe('Component Tests', () => {
    describe('Isdcalc Management Update Component', () => {
        let comp: IsdcalcUpdateComponent;
        let fixture: ComponentFixture<IsdcalcUpdateComponent>;
        let service: IsdcalcService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [IsdtoolsTestModule],
                declarations: [IsdcalcUpdateComponent]
            })
                .overrideTemplate(IsdcalcUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(IsdcalcUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IsdcalcService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Isdcalc(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.isdcalc = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Isdcalc();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.isdcalc = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
