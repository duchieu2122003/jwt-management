import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environment/environment.prod";

const api = environment.API_ADMIN + '/employees';

@Injectable({
  providedIn: "root"
})
export class AdEmployeesService {
  constructor(private http: HttpClient) {
  }

  getPageEmployees(data: any): Observable<any> {
    const apiCustom = api + "?name=" + data.name + "&code=" + data.code + "&email=" + data.email
      + "&city=" + data.city + "&status=" + data.status + "&page=" + data.page + "&size=" + data.size;
    return this.http.get(apiCustom);
  }

  getDetailEmployees(id: string): Observable<any> {
    return this.http.get(api + '/' + id);
  }

  createEmployees(data: any): Observable<any> {
    return this.http.post(api, data);
  }


}
