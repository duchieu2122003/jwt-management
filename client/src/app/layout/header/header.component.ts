import {Component, OnInit} from '@angular/core';
import {jwtDecode} from "jwt-decode";
import {AuthenticatedService} from "../../core/common/service/authenticated.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-common-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  lastName: string = "";
  token: string = "";
  role: string = ""
  userCurrent: any;

  constructor(private authenticated: AuthenticatedService, private toast: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    const token = localStorage.getItem("token");
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
    this.authenticated.logOut().subscribe(data => {
      if (data.data == true) {
        this.toast.success('Đăng xuất thành công', 'Thông báo', {
          timeOut: 2000,
          positionClass: 'toast-top-center',
          progressBar: true
        });
        this.router.navigate(["/common/login"]);
        localStorage.clear();
      } else {
        this.toast.info('Lỗi hệ thống, đăng xuất thất bại', 'Thông báo', {
          timeOut: 2000,
          positionClass: 'toast-top-center',
          progressBar: true
        });
      }
    });

  }
}
