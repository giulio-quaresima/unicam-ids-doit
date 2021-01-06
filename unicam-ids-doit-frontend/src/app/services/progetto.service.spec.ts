import { TestBed } from '@angular/core/testing';

import { ProgettoService } from './progetto.service';

describe('ProgettoService', () => {
  let service: ProgettoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProgettoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
