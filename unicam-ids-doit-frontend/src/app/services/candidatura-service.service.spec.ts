import { TestBed } from '@angular/core/testing';

import { CandidaturaServiceService } from './candidatura-service.service';

describe('CandidaturaServiceService', () => {
  let service: CandidaturaServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CandidaturaServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
