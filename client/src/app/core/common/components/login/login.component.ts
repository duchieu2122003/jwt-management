import {Component, OnInit} from '@angular/core';
import {AuthenticatedService} from "../../service/authenticated.service";
import {jwtDecode} from "jwt-decode";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  title = 'Đăng nhập';
  request: {
    email: string,
    password: string
  } = {
    email: "",
    password: ""
  }
  userCurrent: any;
  errorLogin: string = "";

  constructor(private http: HttpClient, private toast: ToastrService,
              private loginService: AuthenticatedService, private router: Router
  ) {
  }

  ngOnInit(): void {
    const token = localStorage.getItem("token");
    if (token !== undefined && token !== null) {
      this.userCurrent = jwtDecode(token);
      let role = this.userCurrent.role;
      if (role == "ADMIN") {
        this.router.navigate(['/admin']);
      } else if (role == "MANAGER") {
        this.router.navigate(['/manager']);
      } else {
        this.router.navigate(['/staff']);
      }
    }

  }

  showPassword: boolean = false;

  handlePassword() {
    this.showPassword = !this.showPassword;
  }

  onSubmit() {
    if (this.request.email.trim().length == 0 || this.request.password.trim().length == 0) {
      this.errorLogin = "Vui lòng điền đầy đủ thông tin";
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.request.email)) {
      this.errorLogin = "Email sai định dạng";
    } else {
      this.errorLogin = "";
      this.loginService.login(this.request).subscribe({
        next: (response) => {
          if (response.data.authenticated == true) {
            this.toast.success('Đăng nhập thành công', 'Thông báo', {
              timeOut: 2000,
              positionClass: 'toast-top-center',
              progressBar: true
            });
          }
          localStorage.setItem('token', response.data.token);
          this.userCurrent = jwtDecode(response.data.token);
          if (this.userCurrent.role == "ADMIN") {
            this.router.navigate(["/admin"]);
          } else if (this.userCurrent.role == "MANAGER") {
            this.router.navigate(["/manager"]);
          } else {
            this.router.navigate(["/staff"]);

          }
        }, error: (err) => {
          this.toast.error(err.error.message, 'Thông báo', {
            timeOut: 3000,
            positionClass: 'toast-top-center',
            progressBar: true
          });
        }
      })
    }
  }

  onEnterSubmit() {
    this.onSubmit();
  }
}
