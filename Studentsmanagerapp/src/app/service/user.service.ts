import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public isLoggedIn : boolean;

  public user: User;

  constructor() { }
}

interface User {
  name?: string;
}
