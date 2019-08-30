import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountcreateComponent } from './accountcreate.component';

describe('AccountcreateComponent', () => {
  let component: AccountcreateComponent;
  let fixture: ComponentFixture<AccountcreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountcreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountcreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
