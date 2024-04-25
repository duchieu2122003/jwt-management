import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthenticatedService} from "../../service/authenticated.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {EmployeeLogin} from "../../../../entitis/EmployeeLogin";
import {Observable, Subscription} from "rxjs";
import {select, Store} from "@ngrx/store";
import {setEmployeeCurrent} from "../../../../store/employees-current.reduce";
import {CoEmployeesService} from "../../service/co-employees.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit, OnDestroy {
  title = 'Đăng nhập';
  request: {
    email: string,
    password: string
  } = {
    email: "",
    password: ""
  }

  employeeLogin$: Observable<EmployeeLogin>;
  errorLogin: string = "";
  subscription: Subscription;
  employeeCurrent: EmployeeLogin = {
    token: '',
    lastName: '',
    role: ''
  };

  constructor(private toast: ToastrService,
              private coEmployeesService: CoEmployeesService,
              private loginService: AuthenticatedService, private router: Router,
              private store: Store<{ employeeCurrent: EmployeeLogin }>
  ) {
    this.employeeLogin$ = store.pipe(select('employeeCurrent'));
    this.subscription = this.employeeLogin$.subscribe((data: EmployeeLogin) => {
        this.employeeCurrent = data;
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  ngOnInit(): void {
    const token = sessionStorage.getItem("token");
    if (this.employeeCurrent.role === '' && token) {
      this.refreshHeader();
    }
  }

  refreshHeader() {
    this.coEmployeesService.detailEmployeesForHeader().subscribe({
      next: (response) => {
        this.store.dispatch(setEmployeeCurrent({...response.data, token: sessionStorage.getItem('token')}));
        this.redirectByRole(this.employeeCurrent.role);
      }
    })
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
          this.toast.success('Đăng nhập thành công', 'Thông báo', {
            timeOut: 2000,
            positionClass: 'toast-top-center',
            progressBar: true
          });
          sessionStorage.setItem('token', response.data.token);
          this.store.dispatch(setEmployeeCurrent(response.data));
          this.redirectByRole(response.data.role);
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

  redirectByRole(role: string) {
    if (role === "ADMIN") {
      this.router.navigate(['/admin']);
    } else if (role === "MANAGER") {
      this.router.navigate(['/manager']);
    } else if (role === 'STAFF') {
      this.router.navigate(['/staff']);
    }
  }

  onEnterSubmit() {
    this.onSubmit();
  }
}
