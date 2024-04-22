import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment/environment";
import {BehaviorSubject, Observable} from "rxjs";
import {Missions} from "../../../entitis/Missions";

const api = environment.API_MANAGER + `/missions`

@Injectable({
  providedIn: 'root'
})
export  class MaMissionsService {

  missionCreate: BehaviorSubject<Missions> = new BehaviorSubject<Missions>({
    id: '',
    name: '',
    descriptions: ''
  });
   $missionCreate = this.missionCreate.asObservable();

  constructor(private http: HttpClient) {
  }

  getListMission(): Observable<any> {
    return this.http.get(api);
  }

  createMission(data: Missions): Observable<any> {
    return this.http.post(api, data);
  }

  updateMission(data: any): Observable<any> {
    return this.http.put(api, data);
  }

  deleteMission(id: string): Observable<any> {
    return this.http.delete(api + "/" + id);
  }

}
