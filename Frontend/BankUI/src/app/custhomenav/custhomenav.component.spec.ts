import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CusthomenavComponent } from './custhomenav.component';

describe('CusthomenavComponent', () => {
  let component: CusthomenavComponent;
  let fixture: ComponentFixture<CusthomenavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CusthomenavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CusthomenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
