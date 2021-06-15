import { TestBed } from '@angular/core/testing';

import { InvitoService } from './invito.service';

describe('InvitoService', () => {
  let service: InvitoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InvitoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
