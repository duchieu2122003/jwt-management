import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment";
import {Observable} from "rxjs";

const api = environment.API_ADMIN + '/departments';

@Injectable({
  providedIn: "root"
})
export class AdDepartmentService {

  constructor(private http: HttpClient) {
  }

  getAllDepartmentView(): Observable<any> {
    return this.http.get(api + '/get-all');
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
}
