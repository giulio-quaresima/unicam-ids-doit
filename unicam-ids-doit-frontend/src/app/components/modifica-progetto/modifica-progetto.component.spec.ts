import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificaProgettoComponent } from './modifica-progetto.component';

describe('ModificaProgettoComponent', () => {
  let component: ModificaProgettoComponent;
  let fixture: ComponentFixture<ModificaProgettoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModificaProgettoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificaProgettoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
