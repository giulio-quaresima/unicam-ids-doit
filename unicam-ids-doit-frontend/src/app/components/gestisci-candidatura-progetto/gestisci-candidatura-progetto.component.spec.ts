import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestisciCandidaturaProgettoComponent } from './gestisci-candidatura-progetto.component';

describe('GestisciCandidaturaProgettoComponent', () => {
  let component: GestisciCandidaturaProgettoComponent;
  let fixture: ComponentFixture<GestisciCandidaturaProgettoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestisciCandidaturaProgettoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestisciCandidaturaProgettoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
