import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreaProgettoComponent } from './crea-progetto.component';

describe('CreaProgettoComponent', () => {
  let component: CreaProgettoComponent;
  let fixture: ComponentFixture<CreaProgettoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreaProgettoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreaProgettoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
