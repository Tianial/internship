import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        AppComponent //declaration of the component
      ],
    }).compileComponents(); // called   to compile ouR COMPONENT RESOURCES
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  //Block 3 demonstrates how you can have access to the properties of the created component (AppComponent).
  it(`should have as title 'Studentsmanagerapp'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('Studentsmanagerapp');
  });

  // block demonstrates how the test behaves in the browser environment. After creating the component, an instance of the created component (detectChanges) to simulate running on the browser environment is called
  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('Studentsmanagerapp app is running!');
  });
});
