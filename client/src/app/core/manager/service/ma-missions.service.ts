import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment.prod";
import {Observable} from "rxjs";

const api = environment.API_MANAGER + `/missions`

@Injectable({
  providedIn: 'root'
})
export class MaMissionsService {

  constructor(private http: HttpClient) {
  }

  getAllMissions(): Observable<any> {
    return this.http.get(api);
  }

}
