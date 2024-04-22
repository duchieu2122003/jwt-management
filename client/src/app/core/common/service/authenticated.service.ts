import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environment/environment";

@Injectable({
    providedIn: "root"
  }
)
export class AuthenticatedService {

  constructor(private httpClient: HttpClient) {
  }

  login(data: any): Observable<any> {
    return this.httpClient.post(environment.API_COMMON + '/login', data);
  }

  logOut(): Observable<any> {
    return this.httpClient.get(environment.API_COMMON + '/logout');
  }
}
