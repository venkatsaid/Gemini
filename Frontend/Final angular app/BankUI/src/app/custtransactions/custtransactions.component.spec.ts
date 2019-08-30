import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CusttransactionsComponent } from './custtransactions.component';

describe('CusttransactionsComponent', () => {
  let component: CusttransactionsComponent;
  let fixture: ComponentFixture<CusttransactionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CusttransactionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CusttransactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
