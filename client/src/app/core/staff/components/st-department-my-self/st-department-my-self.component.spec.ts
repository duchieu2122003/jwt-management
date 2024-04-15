import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StDepartmentMySelfComponent } from './st-department-my-self.component';

describe('StDepartmentMySelfComponent', () => {
  let component: StDepartmentMySelfComponent;
  let fixture: ComponentFixture<StDepartmentMySelfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StDepartmentMySelfComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StDepartmentMySelfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
