import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaptopdetailsComponent } from './laptopdetails.component';

describe('LaptopdetailsComponent', () => {
  let component: LaptopdetailsComponent;
  let fixture: ComponentFixture<LaptopdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LaptopdetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LaptopdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
