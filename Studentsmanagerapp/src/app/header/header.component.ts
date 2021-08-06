import { Component, OnInit } from '@angular/core';
import {Student} from "../student";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public onOpenModal(student: Student | undefined, mode: string): void {
    /*const container = document.getElementById('main-container');
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
    button.click()*/
  }

  goToAddStudent(): void {
    this.router.navigate(['main/add-new-student'])
  }

  public searchStudents(key: string): void {
    /*console.log(key);
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
    }*/
  }


}
