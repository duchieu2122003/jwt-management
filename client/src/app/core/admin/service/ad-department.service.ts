import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment.prod";
import {Observable} from "rxjs";

const api = environment.API_ADMIN + '/departments';

@Injectable({
  providedIn: "root"
})
export class AdDepartmentService {

  constructor(private http: HttpClient) {
  }

  getAllDepartment(): Observable<any> {
    return this.http.get(api);
  }
}
