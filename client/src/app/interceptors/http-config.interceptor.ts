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
    const token = localStorage.getItem('token');
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    return next.handle(request).pipe(catchError((error: HttpErrorResponse) => {
        console.log("Lỗi interceptorrrrrrr")
        console.log(error);
        if (error.status == 401) {
          this.toast.info('Hết phiên đăng nhập, vui lòng đăng nhập lại', 'Info', {closeButton: true});
          this.router.navigate(['/common/login'])
        }
        if (error.status == 403) {
          this.router.navigate(['/403'])
        }
        return throwError(error)
      }
    ))

  }

}
