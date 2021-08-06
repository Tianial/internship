import { Component, OnInit } from '@angular/core';
import {Student} from "../student";
import {NgForm} from "@angular/forms/forms";
import {HttpErrorResponse} from "@angular/common/http";
import {StudentService} from "../student.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-add-new-student',
  templateUrl: './add-new-student.component.html',
  styleUrls: ['./add-new-student.component.css']
})
export class AddNewStudentComponent implements OnInit {

  public selectedStudentId: string;

  constructor(
    private studentService: StudentService,
    private route:ActivatedRoute

  ) { }

  ngOnInit(): void {
  }

  public onAddStudent(addForm:NgForm):void{
    this.studentService.addStudent(addForm.value).subscribe(
      (response: Student) => {
        console.log(response);
        addForm.reset();


      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();

      }
    )   //service making calls to the backend,the subscribe is to always be notified

  }


  savechanges(): void {


  }

  private onUpdateStudent() {

  }
}
