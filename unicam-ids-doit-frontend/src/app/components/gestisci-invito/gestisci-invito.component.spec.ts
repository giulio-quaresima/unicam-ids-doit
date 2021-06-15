import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestisciInvitoComponent } from './gestisci-invito.component';

describe('GestisciInvitoComponent', () => {
  let component: GestisciInvitoComponent;
  let fixture: ComponentFixture<GestisciInvitoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestisciInvitoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestisciInvitoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
