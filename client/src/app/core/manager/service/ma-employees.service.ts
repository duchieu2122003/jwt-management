import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment.prod";
import {Observable} from "rxjs";


const api = environment.API_MANAGER + '/employees'

@Injectable({
  providedIn: 'root'
})
export class MaEmployeesService {

  constructor(private http: HttpClient) {
  }

  getAllEmployeesNotDepartments(): Observable<any> {
    return this.http.get(api + '/not-departments')
  }
}
