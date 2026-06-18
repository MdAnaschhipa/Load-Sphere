import { TestBed } from '@angular/core/testing';

import { Load } from './load';

describe('Load', () => {
  let service: Load;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Load);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
