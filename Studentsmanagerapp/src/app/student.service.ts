import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Student} from "./student";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class StudentService {


  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  /**
   * getStudent returns an Observable of type Student[]
   */
  public getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiServiceUrl}/student`);
  }

  public getStudentById(studentId: string): Observable<Student> {
    return this.http.get<Student>(`${this.apiServiceUrl}/student/` + studentId);
  }

  public addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(`${this.apiServiceUrl}/student`, student);
  }


  public updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiServiceUrl}/student`, student);
  }

  public deleteStudent(studentId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServiceUrl}/student/${studentId}`);
  }

}

