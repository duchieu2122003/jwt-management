import {Component, Inject, OnInit} from '@angular/core';
import {Missions} from "../../../../../entitis/Missions";
import {MaMissionsService} from "../../../service/ma-missions.service";
import {ToastrService} from "ngx-toastr";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-ma-update-mission',
  templateUrl: './ma-update-mission.component.html',
  styleUrl: './ma-update-mission.component.css'
})
export class MaUpdateMissionComponent implements OnInit {

  objUpdate: Missions = {
    id: '',
    name: '',
    descriptions: ''
  };


  constructor(private maMissionsService: MaMissionsService,
              private dialogRef: MatDialogRef<MaUpdateMissionComponent>,
              private toast: ToastrService,
              @Inject(MAT_DIALOG_DATA) private data: any
  ) {

  }

  ngOnInit(): void {
    this.objUpdate = {
      id: this.data.missionUpdate.id,
      name: this.data.missionUpdate.name,
      descriptions: this.data.missionUpdate.descriptions
    };
  }

  updateMission() {
    this.maMissionsService.updateMission(this.objUpdate).subscribe({
      next: (response) => {
        this.toast.success("Sửa thành công", "Thông báo");
        this.dialogRef.close(response);
      }, error: (err) => {
        this.toast.error(err.error.message);
      }
    })
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
