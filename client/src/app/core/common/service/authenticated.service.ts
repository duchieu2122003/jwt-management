import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environment/environment.prod";
import {Router} from "@angular/router";

@Injectable({
    providedIn: "root"
  }
)
export class AuthenticatedService {

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  login(data: any): Observable<any> {
    return this.httpClient.post(environment.API_COMMON + '/login', data);
  }

  logOut() {
    localStorage.clear();
    this.router.navigate(["/common/login"])
  }
}
