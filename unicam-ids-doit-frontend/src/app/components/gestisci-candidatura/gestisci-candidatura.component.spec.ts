import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestisciCandidaturaComponent } from './gestisci-candidatura.component';

describe('GestisciCandidaturaComponent', () => {
  let component: GestisciCandidaturaComponent;
  let fixture: ComponentFixture<GestisciCandidaturaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestisciCandidaturaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestisciCandidaturaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
