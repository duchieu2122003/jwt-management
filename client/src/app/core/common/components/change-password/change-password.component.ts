import {Component} from '@angular/core';
import {CoEmployeesService} from "../../service/co-employees.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent {

  showPasswordOld: boolean = false;
  showPasswordNew: boolean = false;
  showPasswordReNew: boolean = false;
  errorMessage: string = "";

  data = {
    passwordOld: "",
    passwordNew: "",
  }
  rePasswordNew = "";

  constructor(private coEmployeesService: CoEmployeesService, private toast: ToastrService,
              private router: Router) {
  }

  handleShowPassword(choiceInput: string) {
    if (choiceInput === 'old') {
      this.showPasswordOld = !this.showPasswordOld;
    } else if (choiceInput === 'new') {
      this.showPasswordNew = !this.showPasswordNew;
    } else {
      this.showPasswordReNew = !this.showPasswordReNew;
    }
  }

  handleUpdatePassword() {
    if (this.data.passwordNew !== this.rePasswordNew) {
      this.errorMessage = "Mật khẩu mới không trùng nhau, vui lòng nhập lại"
      this.rePasswordNew = '';
      return;
    }
    this.coEmployeesService.changePasswordUserCurrent(this.data).subscribe({
      next: (response) => {
        if (response.data) {
          this.toast.success("Thay đổi mật khẩu thành công", "Thông báo")
        } else {
          this.toast.error("Lỗi hệ thống", 'Thông báo');
        }
        this.router.navigate(['/common/information-my-self'])
      }, error: (err) => {
        this.toast.error(err.error.message, 'Thông báo');
      }
    });
  }

  redirectTo(url: string) {
    this.router.navigate([`` + url])
  }
}
