import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {CredentialsDto} from "../common/model/credentials.dto";
import {User} from "../common/model/user";

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  constructor(
    private http: HttpClient
  ) {
  }

  login(credentials: CredentialsDto): Observable<User> {
    return this.http.post(`${environment.apiBaseUrl}/auth/login`, credentials);
  }

}
