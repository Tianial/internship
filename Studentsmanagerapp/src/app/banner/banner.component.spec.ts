import {ComponentFixture, ComponentFixtureAutoDetect, TestBed} from '@angular/core/testing';

import {BannerComponent} from './banner.component';

fdescribe('BannerComponent', () => {
  let component: BannerComponent;
  let fixture: ComponentFixture<BannerComponent>;
  let h1: HTMLElement;

  // runs before any other block. Other blocks do not depend on each other to run.
  beforeEach(async () => {
    //Testbed used to configure the testing environment and configurE the testing module.
    // Allows overriding default providers, directives, pipes, modules of the test injector, which are defined in test_injector.js
    await TestBed.configureTestingModule({
      declarations: [BannerComponent],  // BannerComponent is the main component we want to have in this testing environment.
      providers: [   // Then add ComponentFixtureAutoDetect to the providers array of the testing module configuration for Automatic change detection
        { provide: ComponentFixtureAutoDetect, useValue: true }
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BannerComponent); // Creates component
    h1 = fixture.nativeElement.querySelector('h1');
    component = fixture.componentInstance; //
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
    expect(h1.textContent).toEqual('');
  });

  it('should display original title after detectChanges() in html(DOM)', () => {
    expect(component.title).toEqual('banner works');
    fixture.detectChanges();
    expect(h1.textContent).toEqual('banner works');
  });

});
