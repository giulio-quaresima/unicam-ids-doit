import { TestBed } from '@angular/core/testing';

import { SoggettoCollettivoService } from './soggetto-collettivo.service';

describe('SoggettoCollettivoService', () => {
  let service: SoggettoCollettivoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SoggettoCollettivoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
