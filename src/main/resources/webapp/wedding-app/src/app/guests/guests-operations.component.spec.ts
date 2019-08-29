import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestsOperationsComponent } from './guests-operations.component';

describe('GuestsOperationsComponent', () => {
  let component: GuestsOperationsComponent;
  let fixture: ComponentFixture<GuestsOperationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuestsOperationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestsOperationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
