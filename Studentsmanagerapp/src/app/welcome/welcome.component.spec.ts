import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomeComponent } from './welcome.component';
import {UserService} from "../service/user.service";
import {DebugElement} from "@angular/core/core";

describe('WelcomeComponent', () => {
  let component: WelcomeComponent;
  let fixture: ComponentFixture<WelcomeComponent>; // Fixture is the testing environment for this component and provides acces to the comp. itself
  let de: DebugElement //HTML




  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WelcomeComponent ]

      //TODO: providers: [ { provide: UserService, useValue: userServiceStub } ],

    })
    .compileComponents(); //compiles the component html and css
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
