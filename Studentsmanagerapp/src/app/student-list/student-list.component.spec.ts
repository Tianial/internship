import {ComponentFixture, TestBed} from '@angular/core/testing';

import {StudentListComponent} from './student-list.component';
import {Location} from "@angular/common";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {StudentEditComponent} from "../student-edit/student-edit.component";
import {Router} from "@angular/router";
import {StudentService} from "../student.service";
import {Observable, of} from "rxjs";
import {Student} from "../student";
import {AddNewStudentComponent} from "../add-new-student/add-new-student.component";

fdescribe('StudentListComponent', () => {
  let component: StudentListComponent;
  let fixture: ComponentFixture<StudentListComponent>;
  let location: Location;
  let router: Router;

  let studentServiceMock: Partial<StudentService> = {
    getStudents(): Observable<Student[]> {
      return of();
    }
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StudentListComponent],
      imports: [
        RouterTestingModule.withRoutes([
          {path: 'main/edit-student/:id', component: StudentEditComponent},
          {path: 'main/add-new-student',component: AddNewStudentComponent}
        ]),
        HttpClientTestingModule
      ],
      providers: [
        {provide: StudentService, useValue: studentServiceMock}
      ]

    })
      .compileComponents();
  });

  beforeEach(() => {
    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
    router.setUpLocationChangeListener();

    fixture = TestBed.createComponent(StudentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate when click on edit button', () => {
    console.log({component});
    component.goToEditStudent(2)
//whenStable() can be used to resume testing after events have triggered asynchronous activity or asynchronous change detection.
    fixture.whenStable().then(() => {
      // path of navigation is store in location.path and should correspond to '/main/edit-student/2'
      expect(location.path()).toBe('/main/edit-student/2');
    })

  });

  it('should show number of students lists require', () => {

    const students: Student[] = [
      {id: 1, name: 'Bob'},
      {id: 2, name: 'Alice'},
      {id: 3, name: 'John'}
    ];

    // When -> Then resolve user data
    spyOn(studentServiceMock, 'getStudents') // spyOn lets know that studentServiceMock is a sy and can be use so.and consequently used in the expects
      .and
      .returnValue(of(students)); // returns list of student

    component.getStudentList();

    // Assertions
    //expect that getStudents has been called  once.hier the mock ask as the true service for the response not to be called again
    expect(studentServiceMock.getStudents).toHaveBeenCalledTimes(1);
    expect(studentServiceMock.getStudents).toHaveBeenCalledWith();
    expect(component.students).toBeDefined();
    expect(component.students.length).toEqual(students.length); //expect that only the 3 students (length) of the students called
  });

  it('should navigate when click on Addnewstudent ', () => {
    console.log({component});
    component.goToAddStudent()
//whenStable() can be used to resume testing after events have triggered asynchronous activity or asynchronous change detection.
    fixture.whenStable().then(() => {
      // path of navigation is store in location.path and should correspond to '/main/edit-student/2'
      expect(location.path()).toBe('/main/add-new-student');
    })

  });

});


