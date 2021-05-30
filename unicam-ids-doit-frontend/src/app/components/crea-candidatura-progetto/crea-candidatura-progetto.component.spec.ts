import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreaCandidaturaProgettoComponent } from './crea-candidatura-progetto.component';

describe('CreaCandidaturaProgettoComponent', () => {
  let component: CreaCandidaturaProgettoComponent;
  let fixture: ComponentFixture<CreaCandidaturaProgettoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreaCandidaturaProgettoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreaCandidaturaProgettoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
