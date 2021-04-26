import {Component, OnInit} from '@angular/core';
import {Student} from "./student";
import {StudentService} from "./student.service";
import {HttpErrorResponse} from "@angular/common/http/http.d";
import {NgForm} from "@angular/forms/forms.d";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

//implements OnInit and override the fxn ngOninit b/c for any initialisation the fxn ngOnit will be called
export class AppComponent implements OnInit {

  public title = 'Students-Manager-App';

  public students: Student[] | undefined;

  public editStudent: Student | undefined;
  public  deleteStudent: Student | undefined;

  constructor(private studentService: StudentService) {
    console.log("constructor")
  }

  ngOnInit(): void {
    console.log("ngOnInit")
    this.getStudentList(); // activate when cors problem with backend is arranged
    /*this.students = [
      {
        id: 1,
        name: "Tiani",
        email: "tiani@parcaune.com",
        dob: new Date("2017-06-21"),
        age: 4,
        phone: "45678908765",
        imageUrl: 'assets/images/pic1.jpeg',
        studentCode: '5566',
        studentCategory:'Low level student'

      },
      {
        id: 2,
        name: "Alice",
        email: "alice@parcaune.com",
        dob: new Date("2018-06-21"),
        age: 4,
        phone: '34567890',
        imageUrl: 'assets/images/pic3.jpeg',
        studentCode: '6789',
        studentCategory:'High level student'

      }
    ];

     */
  }

  public getStudentList(): void {
    this.studentService.getStudent() // Observable
      .subscribe( // Observer
        (response: Student[]) => {
          console.log({response})
          this.students = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        },
        () => {
        }
      );

  }

  public onAddStudent(addForm:NgForm):void{
    // @ts-ignore
    document.getElementById('add-student-form').click();// when we save the form should go away thanks to this line.the click() is to acess to the click
    this.studentService.addStudent(addForm.value).subscribe(
      (response: Student) => {
        console.log(response);
        this.getStudentList();
        addForm.reset();


      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();

      }
    )   //service making calls to the backend,the subscribe is to alays be notified

  }


public onUpdateStudent(student:Student):void{
    // @ts-ignore
    document.getElementById('add-student-form').click();// when we save the form should go away thanks to this line.the click() is to acess to the click
    this.studentService.updateStudent(student).subscribe(
      (response: Student) => {
        console.log(response);
        this.getStudentList();

      },
      (error: HttpErrorResponse) => {
        alert(error.message);

      }
    )   //service making calls to the backend,the subscribe is to alays be notified

  }

  public onDeleteStudent(studentId:number|undefined):void{
    // @ts-ignore
    document.getElementById('add-student-form').click();// when we save the form should go away thanks to this line.the click() is to acess to the click
    //deleteStudent called from the service
    this.studentService.deleteStudent(studentId as number).subscribe(
      (response: void) => {
        console.log(response);
        this.getStudentList();

      },
      (error: HttpErrorResponse) => {
        alert(error.message);

      }
    )   //service making calls to the backend,the subscribe is to alays be notified

  }

  public searchStudents(key: string): void {
    console.log(key);
    const results: Student[] = [];
    // @ts-ignore
    for (const student of this.students) {
      if (student.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || student.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || student.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || student.studentCategory.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(student);
      }
    }
    this.students = results;
    if (results.length === 0 || !key) {
      this.getStudentList();
    }
  }



  public onOpenModal(student: Student | undefined, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {

      button.setAttribute('data-target','#addStudentModal'); //# b/c we are dealing with Id
    }

    if (mode === 'edit') {
      this.editStudent = student;

      button.setAttribute('data-target','#updateStudentModal');
    }

    if (mode === 'delete') {
      this.deleteStudent = student;

      button.setAttribute('data-target','#deleteStudentModal');
    }
    // @ts-ignore
    container.appendChild(button);
    button.click()
  }

}
