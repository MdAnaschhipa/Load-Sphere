import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLoad } from './add-load';

describe('AddLoad', () => {
  let component: AddLoad;
  let fixture: ComponentFixture<AddLoad>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddLoad]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddLoad);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
