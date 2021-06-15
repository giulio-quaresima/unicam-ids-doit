import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListInvitiComponent } from './list-inviti.component';

describe('ListInvitiComponent', () => {
  let component: ListInvitiComponent;
  let fixture: ComponentFixture<ListInvitiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListInvitiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListInvitiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
