import {Component} from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent {

  title = 'banner works';
  hideContent = true;
  severity = 450;
  name: string;

  toggle() {
    this.hideContent = !this.hideContent;
  }



}
