import {Component, OnInit} from '@angular/core';
import {CredentialsDto} from "../common/model/credentials.dto";
import {Router} from "@angular/router";
import {AuthentificationService} from "../service/authentification.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public credentials: CredentialsDto = {
    username: null,
    password: null
  };

  public errorMessage: string;

  constructor(
    private router: Router,
    private authService: AuthentificationService
  ) {
  }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.login(this.credentials)
      .subscribe(
        {
          next: (userResponse) => {
            this.router.navigate(['/main']);
          },
          error: (errorResponse) => {
            this.errorMessage = 'Login failed. Please try again.';
            console.error({errorResponse})
          },
        });
    //
  }


}
