import {ComponentFixture, ComponentFixtureAutoDetect, async, TestBed, waitForAsync} from '@angular/core/testing';

import {BannerComponent} from './banner.component';
import {FormsModule} from "@angular/forms";

describe('BannerComponent', () => {
  let component: BannerComponent;
  let fixture: ComponentFixture<BannerComponent>;
  let h1: HTMLElement;

  // runs before any other block. Other blocks do not depend on each other to run.
  beforeEach(async () => {
    //Testbed used to configure the testing environment and configurE the testing module.
    // Allows overriding default providers, directives, pipes, modules of the test injector, which are defined in test_injector.js
    await TestBed.configureTestingModule({
      imports: [
        FormsModule
      ],
      declarations: [BannerComponent]  // BannerComponent is the main component we want to have in this testing environment.
     // providers: [   // Then add ComponentFixtureAutoDetect to the providers array of the testing module configuration for Automatic change detection
     //   { provide: ComponentFixtureAutoDetect, useValue: true }
     // ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BannerComponent); // Creates component
    h1 = fixture.nativeElement.querySelector('h1');// selects the title in the html and saves it in the the variable h1
    component = fixture.componentInstance; //initialising the component to be an instance of a class
  });

  it('should create', () => {
    expect(component).toBeTruthy();// checking if the component exists
  });

  it('should display original title', () => {
    fixture.detectChanges(); // refreshes the HTML and ts file
    expect(h1.textContent).toContain(component.title); // will
  });


  it('no title in the html(DOM) after createComponent()', () => {
    expect(component.title).toEqual('banner works');
    expect(h1.textContent).toEqual(''); // by default there is is no title in html file
  });

  it('should display original title after detectChanges() in html(DOM)', () => {
    expect(component.title).toEqual('banner works');
    fixture.detectChanges();
    expect(h1.textContent).toEqual('banner works');
  });

  it('should  togglt the message', () => {
    expect(component.hideContent).toBeTruthy();
    component.toggle();
    expect(component.hideContent).toBeFalsy();

  });


  it('should convert hero name to Title Case', waitForAsync(() => {
    fixture.detectChanges();  // ngOnInit()

    fixture.whenStable().then(() => {
      // get the name's input and display elements from the DOM
      const hostElement: HTMLElement = fixture.nativeElement;
      const nameInput: HTMLInputElement = hostElement.querySelector('input');
      const nameDisplay: HTMLElement = hostElement.querySelector('span'); // element selector

      // simulate user entering a new name into the input box
      nameInput.value = 'Tiani';

      // Dispatch a DOM event so that Angular learns of input value change.
      // In older browsers, such as IE, you might need a CustomEvent instead. See
      // https://developer.mozilla.org/en-US/docs/Web/API/CustomEvent/CustomEvent#Polyfill
      nameInput.dispatchEvent(new Event('input'));

      // Tell Angular to update the display binding through the title pipe
      fixture.detectChanges();

      console.log({nameDisplay});
      console.log({component: component.name});

      expect(nameDisplay.textContent).toBe('Tiani');
    });
  }));

});
