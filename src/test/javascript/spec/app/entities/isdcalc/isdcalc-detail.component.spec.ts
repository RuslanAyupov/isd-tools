/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IsdtoolsTestModule } from '../../../test.module';
import { IsdcalcDetailComponent } from 'app/entities/isdcalc/isdcalc-detail.component';
import { Isdcalc } from 'app/shared/model/isdcalc.model';

describe('Component Tests', () => {
    describe('Isdcalc Management Detail Component', () => {
        let comp: IsdcalcDetailComponent;
        let fixture: ComponentFixture<IsdcalcDetailComponent>;
        const route = ({ data: of({ isdcalc: new Isdcalc(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [IsdtoolsTestModule],
                declarations: [IsdcalcDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(IsdcalcDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(IsdcalcDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.isdcalc).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
