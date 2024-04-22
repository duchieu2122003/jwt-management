import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environment/environment";

const api = environment.API_STAFF + `/departments`;

@Injectable({
  providedIn: 'root'
})
export class StDepartmentService {

  constructor(private http: HttpClient) {
  }

  getDepartmentUserCurrent(): Observable<any> {
    return this.http.get(api);
  }
}
