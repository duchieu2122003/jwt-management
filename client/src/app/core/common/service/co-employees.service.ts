import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment.prod";
import {Observable} from "rxjs";


const api = environment.API_COMMON + "/employees"

@Injectable({
  providedIn: "root"
})
export class CoEmployeesService {

  constructor(private http: HttpClient) {
  }

  detailUserCurrent(): Observable<any> {
    return this.http.get(api);
  }

  updateUserCurrent(data: any):Observable<any> {
    return this.http.put(api, data);
  }

  changePasswordUserCurrent(data: any):Observable<any> {
    return this.http.put(api + `/change-password`, data);
  }

}
