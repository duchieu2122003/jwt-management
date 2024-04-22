import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment";
import {Observable} from "rxjs";

const api = environment.API_MANAGER + '/departments';

@Injectable({
  providedIn: "root"
})
export class MaDepartmentsService {

  constructor(private http: HttpClient) {
  }
  
  getDepartmentsUserCurrent(): Observable<any> {
    return this.http.get(api + '/user-current');
  }
}
