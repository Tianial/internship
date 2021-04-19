import {Component, OnInit} from '@angular/core';
import {Student} from "./student";
import {StudentService} from "./student.service";
import {HttpErrorResponse} from "@angular/common/http/http.d";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

//implements OnInit and override the fxn ngOninit b/c for any initialisation the fxn ngOnit will be called
export class AppComponent implements OnInit {

  public title = 'Studentsmanagerapp';

  public students: Student[] | undefined;

  constructor(private studentService: StudentService) {
    console.log("constructor")
  }

  ngOnInit(): void {
    console.log("ngOnInit")
    //this.getStudentList(); // activate when cors problem with backend is arranged
    this.students = [
      {
        id: 1,
        name: "Tiani",
        email: "tiani@parcaune.com",
        dob: new Date("2017-06-21"),
        age: 4
      },
      {
        id: 2,
        name: "Alice",
        email: "alice@parcaune.com",
        dob: new Date("2018-06-21"),
        age: 4
      }
    ];
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

}
