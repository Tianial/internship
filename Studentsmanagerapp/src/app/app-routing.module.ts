import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {MainComponent} from "./main/main.component";
import {StudentListComponent} from "./student-list/student-list.component";
import {AddNewStudentComponent} from "./add-new-student/add-new-student.component";
import {StudentEditComponent} from "./student-edit/student-edit.component";

const MAIN_CHILD_ROUTES: Routes = [
  {
    path: 'student-list',
    component: StudentListComponent
  },
  {
    path: 'add-new-student',
    component: AddNewStudentComponent
  },
  {
    path: 'edit-student/:id',
    component: StudentEditComponent
  },
  {
    path: '**',
    redirectTo: 'student-list'
  },
];

const routes: Routes = [
  {
    path: '', redirectTo: 'login', pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },

  {
    path: 'main',
    component: MainComponent,
    children: MAIN_CHILD_ROUTES
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
