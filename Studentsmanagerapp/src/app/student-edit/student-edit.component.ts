import {Component, OnInit} from '@angular/core';
import {Student} from "../student";
import {StudentService} from "../student.service";
import {ActivatedRoute} from "@angular/router";
import {Router} from "@angular/router";

@Component({
  selector: 'app-student-edit',
  templateUrl: './student-edit.component.html',
  styleUrls: ['./student-edit.component.css']
})
export class StudentEditComponent implements OnInit {

  private selectedStudentId: string;
  public student: Student;

  constructor(
    private studentService: StudentService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.selectedStudentId = this.route.snapshot.params['id'];

    this.studentService.getStudentById(this.selectedStudentId) // Observable
      .subscribe( // Observer
        (response: Student) => {
          console.log({response})
          this.student = response;
        },
        (error) => {
          console.log(error);
        },
        () => {
        }
      );
  }

  public selectedStudentID: string;

  public editStudent: Student | undefined;


  public onUpdateStudent(student: Student): void {
    this.studentService.updateStudent(this.student).subscribe(data => {
      this.router.navigate(['/main']);
    });
  }

}
