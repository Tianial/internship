import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {StudentService} from "./student.service";
import {FormsModule} from "@angular/forms";
import {LoginComponent} from './login/login.component';
import {AppRoutingModule} from "./app-routing.module";
import {StudentListComponent} from './student-list/student-list.component';
import {HeaderComponent} from './header/header.component';
import {MainComponent} from './main/main.component';
import {AddNewStudentComponent} from './add-new-student/add-new-student.component';
import { StudentEditComponent } from './student-edit/student-edit.component';
import { StudentDeleteComponent } from './student-delete/student-delete.component';
import { BannerComponent } from './banner/banner.component';
import { WelcomeComponent } from './welcome/welcome.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StudentListComponent,
    HeaderComponent,
    MainComponent,
    AddNewStudentComponent,
    StudentEditComponent,
    StudentDeleteComponent,
    BannerComponent,
    WelcomeComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [StudentService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
