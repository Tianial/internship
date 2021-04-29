import {Component, OnInit} from '@angular/core';
import {CredentialsDto} from "../common/model/credentials.dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public credentials: CredentialsDto = {
    username: '',
    password: ''
  };


  constructor(
    private router: Router,
  ) {
  }

  ngOnInit(): void {
  }

  login(): void {
    this.router.navigate(['/main']);
  }

}
