import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment.prod";
import {Observable} from "rxjs";

const api = environment.API_MANAGER + '/departments';

@Injectable({
  providedIn: "root"
})
export class MaDepartmentsService {

  constructor(private http: HttpClient) {
  }

  getAllDepartments(): Observable<any> {
    return this.http.get(api);
  }

  createDepartments(data: any): Observable<any> {
    return this.http.post(api, data);
  }

  updateDepartments(data: any): Observable<any> {
    return this.http.put(api, data);
  }

  deleteDepartments(id: string): Observable<any> {
    return this.http.delete(api + `/` + id)
  }

  // getOneDepartmentById(id: string): Observable<any> {
  //   return this.http.get(api + '/' + id);
  // }

  getOneDepartmentByUserCurrent(): Observable<any> {
    return this.http.get(api + '/employees-missions-department');
  }

  getDepartmentsUserCurrent(): Observable<any> {
    return this.http.get(api + '/user-current');
  }
}
