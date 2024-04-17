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

  updateEmployees(data: any): Observable<any> {
    return this.http.put(api, data);
  }


  deleteEmployees(data: any): Observable<any> {
    return this.http.delete(api + "/" + data);
  }

  getAllCity(): Observable<any> {
    return this.http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/province",
      {headers: {"token": "1a328d4f-fc71-11ee-b6f7-7a81157ff3b1"}})
  }

  getStreetByIdCountry(provinceId: string): Observable<any> {
    return this.http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/district",
      {
        headers: {"token": "1a328d4f-fc71-11ee-b6f7-7a81157ff3b1"}, params: {
          province_id: provinceId
        }
      })
  }

}
