import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment.prod";
import {Observable} from "rxjs";


const api = environment.API_MANAGER + '/employees-missions'

@Injectable({
  providedIn: 'root'
})
export class MaEmployeesMissionsService {

  constructor(private http: HttpClient) {
  }

  getAllEmployeesMissionOnDepartment(id: string): Observable<any> {
    return this.http.get(api + '/' + id)
  }
}
