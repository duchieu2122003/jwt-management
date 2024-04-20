import {Component} from '@angular/core';
import {MaMissionsService} from "../../../service/ma-missions.service";
import {Missions} from "../../../../../entitis/Missions";
import {ToastrService} from "ngx-toastr";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-ma-create-mission',
  templateUrl: './ma-create-mission.component.html',
  styleUrl: './ma-create-mission.component.css'
})
export class MaCreateMissionComponent {

  objCreate: Missions = {
    id: '',
    name: '',
    descriptions: '',
  }

  constructor(private maMissionsService: MaMissionsService,
              private dialogRef: MatDialogRef<MaCreateMissionComponent>,
              private toast: ToastrService
  ) {
  }

  createMissions() {
    this.maMissionsService.createMission(this.objCreate).subscribe({
      next: (response) => {
        this.maMissionsService.missionCreate.next(response.data);
        this.toast.success("Thêm thành công", "Thông báo");
        this.closeDialog();
      }, error: (err) => {
        this.toast.error(err.error.message);
      }
    });
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
