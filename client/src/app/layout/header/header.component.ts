import {Component, OnInit} from '@angular/core';
import {AuthenticatedService} from "../../core/common/service/authenticated.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {EmployeeLogin} from "../../entitis/EmployeeLogin";
import {select, Store} from "@ngrx/store";
import {CoEmployeesService} from "../../core/common/service/co-employees.service";
import {setEmployeeCurrent} from "../../store/employees-current.reduce";

@Component({
  selector: 'app-common-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  employeeLogin$: Observable<EmployeeLogin>;
  employeeCurrent: EmployeeLogin = {
    token: '',
    role: '',
    lastName: '',
    id: ''
  };

  constructor(private authenticated: AuthenticatedService,
              private toast: ToastrService,
              private coEmployeesService: CoEmployeesService,
              private router: Router,
              private store: Store<{ employeeCurrent: EmployeeLogin }>) {
    this.employeeLogin$ = store.pipe(select('employeeCurrent'));
    this.employeeLogin$.subscribe((data: EmployeeLogin) => {
      this.employeeCurrent = data;
    })
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
      }
    })
  }

  logout() {
    this.authenticated.logOut().subscribe(data => {
      if (data.data == true) {
        this.toast.success('Đăng xuất thành công', 'Thông báo', {
          timeOut: 2000,
          positionClass: 'toast-top-center',
        });
        this.router.navigate(["/common/login"]);
        sessionStorage.clear();
      } else {
        this.toast.info('Lỗi hệ thống, đăng xuất thất bại', 'Thông báo', {
          timeOut: 2000,
          positionClass: 'toast-top-center',
        });
      }
    });
  }

}
