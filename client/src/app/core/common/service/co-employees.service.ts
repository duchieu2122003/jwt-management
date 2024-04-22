import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment";
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
