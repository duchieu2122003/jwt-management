import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environment/environment.prod";

const api = environment.API_STAFF + `/employees`;

@Injectable({
  providedIn: 'root'
})
export class StEmployeesService {

  constructor(private http: HttpClient) {
  }

  getListEmployeesOnDepartmentsUserCurrent(): Observable<any> {
    return this.http.get(api);
  }
}
