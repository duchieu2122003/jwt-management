import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MaEmployeesMissionsService} from "../../../service/ma-employees-missions.service";
import {MaMissionsService} from "../../../service/ma-missions.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-ma-modal-update-employee-mission',
  templateUrl: './ma-modal-update-employee-mission.component.html',
  styleUrl: './ma-modal-update-employee-mission.component.css'
})
export class MaModalUpdateEmployeeMissionComponent implements OnInit {

  objDetail: {
    employeesId: string,
    code: string,
    email: string,
    fullName: string,
    missionsListId: string[]
  } = {
    employeesId: '',
    code: '',
    email: '',
    fullName: '',
    missionsListId: []
  };

  listMissions: {
    id: string,
    name: string
  }[] = [];

  constructor(private dialogRef: MatDialogRef<MaModalUpdateEmployeeMissionComponent>,
              @Inject(MAT_DIALOG_DATA) private idEmployees: string,
              private maEmployeesMissionsService: MaEmployeesMissionsService,
              private maMissionsService: MaMissionsService,
              private toast: ToastrService,
  ) {
  }

  ngOnInit(): void {
    this.getAllMissions();
    this.getDetailEmployeesMissions();
  }

  getDetailEmployeesMissions() {
    this.maEmployeesMissionsService
      .detailEmployeesMissionToUpdateByIdEmployees(this.idEmployees).subscribe({
      next: (response) => {
        this.objDetail = response.data;
      }
    })
  }

  getAllMissions() {
    this.maMissionsService.getListMission().subscribe({
      next: (response) => {
        this.listMissions = response.data;
      }
    })
  }

  onCloseDialog() {
    this.dialogRef.close();
  }

  handleUpdate() {
    const data = {
      employeesId: this.objDetail.employeesId,
      missionsListId: this.objDetail.missionsListId
    }
    this.maEmployeesMissionsService.updateMissionsEmployeesList(data).subscribe({
      next: (response) => {
        this.toast.success('Sửa thành công', 'Thông báo');
        this.dialogRef.close(response);
      }, error: (err) => {
        this.toast.error(err.error.message, 'Thông báo');
      }
    });
  }

}
