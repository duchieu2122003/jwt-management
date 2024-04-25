import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: "root"
})

export class HttpConfigInterceptor implements HttpInterceptor {

  constructor(private router: Router, private toast: ToastrService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = sessionStorage.getItem('token');
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    return next.handle(request).pipe(catchError((error: HttpErrorResponse) => {
      if (error.status == 401) {
        this.toast.error('Hết phiên đăng nhập, vui lòng đăng nhập lại', 'Thông báo');
        this.router.navigate(['/common/login'])
      } else if (error.status == 403) {
        this.toast.error('Không có quyền đăng nhập chức vụ này, vui lòng đăng nhập lại', 'Thông báo');
        this.router.navigate(['/403'])
      } else if (error.status == 500) {
        this.toast.error('Không có quyền đăng nhập chức vụ này, vui lòng đăng nhập lại', 'Thông báo');
        this.router.navigate(['/403'])
      }
      return throwError(error.message);
    }));
  }

}
