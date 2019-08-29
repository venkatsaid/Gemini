import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TranfertomeComponent } from './tranfertome.component';

describe('TranfertomeComponent', () => {
  let component: TranfertomeComponent;
  let fixture: ComponentFixture<TranfertomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TranfertomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TranfertomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
