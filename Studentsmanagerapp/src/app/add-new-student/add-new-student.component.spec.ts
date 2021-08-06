import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewStudentComponent } from './add-new-student.component';
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {Router} from "@angular/router/router";
import {Location} from "@angular/common/common";

describe('AddNewStudentComponent', () => {
  let component: AddNewStudentComponent;
  let fixture: ComponentFixture<AddNewStudentComponent>;
  let location: Location;
  let router: Router;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewStudentComponent ],
      imports: [
        RouterTestingModule.withRoutes([
          {path: 'main/add-new-student', component: AddNewStudentComponent}
        ]),
        HttpClientTestingModule
        ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
    router.setUpLocationChangeListener();

    fixture = TestBed.createComponent(AddNewStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
