import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-forbidden',
  templateUrl: './forbidden.component.html',
  styleUrl: './forbidden.component.css'
})
export class ForbiddenComponent {

  constructor(private router: Router) {
  }

  redirectLogin() {
    sessionStorage.clear();
    this.router.navigate(['common/login'])
  }
}
