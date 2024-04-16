import {Component, OnInit} from '@angular/core';
import {jwtDecode} from "jwt-decode";
import {AuthenticatedService} from "../../../core/common/service/authenticated.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-common-header',
  templateUrl: './common-header.component.html',
  styleUrl: './common-header.component.css'
})
export class CommonHeaderComponent implements OnInit {
  lastName: string = "";
  token: string = "";
  role: string = ""
  userCurrent: any;

  constructor(private authenticated: AuthenticatedService, private toast: ToastrService) {
  }

  ngOnInit(): void {
    let token = localStorage.getItem("token");
    if (token != null) {
      this.token = token;
      if (jwtDecode(token) != null) {
        this.userCurrent = jwtDecode(token);
        this.role = this.userCurrent.role;
        this.lastName = this.userCurrent.name;
      }
    }
  }

  logout() {
    this.toast.success('Đăng xuất thành công', 'Thông báo', {
      timeOut: 2000,
      positionClass: 'toast-top-center',
      progressBar: true
    });
    this.authenticated.logOut();
  }
}
