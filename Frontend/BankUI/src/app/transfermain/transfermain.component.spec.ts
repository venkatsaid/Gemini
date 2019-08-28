import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransfermainComponent } from './transfermain.component';

describe('TransfermainComponent', () => {
  let component: TransfermainComponent;
  let fixture: ComponentFixture<TransfermainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransfermainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransfermainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
