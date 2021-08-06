import {ComponentFixture, TestBed} from '@angular/core/testing';
import {Location} from '@angular/common';
import {Router} from "@angular/router";
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {FormsModule} from "@angular/forms";
import {Observable, of, throwError} from "rxjs";

import {LoginComponent} from './login.component';
import {AuthentificationService} from "../service/authentification.service";
import {User} from "../common/model/user";
import {CredentialsDto} from "../common/model/credentials.dto";
import {MainComponent} from "../main/main.component";

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let location: Location;
  let router: Router;

  let authServiceMock: Partial<AuthentificationService> = {
    login(credentials: CredentialsDto): Observable<User> {
      return of();
    }
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [
        RouterTestingModule.withRoutes([
          {path: 'main', component: MainComponent}
        ]),
        HttpClientTestingModule,
        FormsModule
      ],
      providers: [
        {provide: AuthentificationService, useValue: authServiceMock}
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
    router.setUpLocationChangeListener();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a void login form on init', () => {
    expect(component.credentials.username).toBe(null);
    expect(component.credentials.password).toBe(null);
  });

  it('should show errors when submitting void form', () => {
    const loginButtonElement: HTMLElement = fixture.nativeElement.querySelector('#loginButton'); // id selector of button
    loginButtonElement.click();

    fixture.whenStable().then(() => {
      fixture.detectChanges();

      //NodeList an array of HTML element
      const errorMessagesElements: NodeList = fixture.nativeElement.querySelectorAll('.text-danger');//class

      expect(errorMessagesElements.length).toEqual(2);
      expect(errorMessagesElements.item(0).textContent).toContain('This field is required');
      expect(errorMessagesElements.item(1).textContent).toContain('This field is required');
    });

  });

  it('should login user successful and navigate to main component', () => {
    // Given
    const fakeCredentials: CredentialsDto = {
      username: 'admin',
      password: 'pwd-admin'
    };
    const fakeUserData: User = {
      id: 1,
      username: 'admin'
    };
    component.credentials = fakeCredentials;

    fixture.detectChanges();

    // When -> Then resolve user data
    spyOn(authServiceMock, 'login').and.returnValue(of(fakeUserData));

    component.login();

    // Assertions
    expect(authServiceMock.login).toHaveBeenCalledTimes(1);
    expect(authServiceMock.login).toHaveBeenCalledWith(fakeCredentials);

    fixture.whenStable().then(() => {
      expect(location.path()).toBe('/main');
    });
  });

  it('should show errors messages on login failed and not navigate', () => {
    // Given
    const fakeCredentials: CredentialsDto = {
      username: 'admin',
      password: 'pwd-admin'
    };
    component.credentials = fakeCredentials;

    fixture.detectChanges();

    // When -> Then throw error
    spyOn(authServiceMock, 'login').and.returnValue(throwError({}));

    component.login();

    // Assertions
    expect(authServiceMock.login).toHaveBeenCalledTimes(1);
    expect(authServiceMock.login).toHaveBeenCalledWith(fakeCredentials);
    expect(component.errorMessage).toBe('Login failed. Please try again.');

    fixture.detectChanges();

    fixture.whenStable().then(() => {
      const errorDivElement: HTMLElement = fixture.nativeElement.querySelector('#loginErrorMsg');
      expect(errorDivElement.textContent).toContain('Login failed. Please try again.');
    });
  });

})
