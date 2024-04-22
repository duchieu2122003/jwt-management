import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";
import {MaEmployeesService} from "../../../service/ma-employees.service";
import {MaMissionsService} from "../../../service/ma-missions.service";
import {MaEmployeesMissionsService} from "../../../service/ma-employees-missions.service";

@Component({
  selector: 'app-ma-modal-create-employee-mission',
  templateUrl: './ma-modal-create-employee-mission.component.html',
  styleUrl: './ma-modal-create-employee-mission.component.css'
})
export class MaModalCreateEmployeeMissionComponent implements OnInit {

  listEmployees: {
    id: string,
    email: string,
    code: string,
    fullName: string
  }[] = [];

  listMissions: {
    id: string,
    name: string
  }[] = [];

  listEmployeesSelected: string[] = [];
  listEmployeesAdd: {
    stt: number,
    id: string,
    email: string,
    code: string,
    fullName: string,
    missionsListId: string[]
  }[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) private idDepartment: string,
              private dialog: MatDialogRef<MaModalCreateEmployeeMissionComponent>,
              private maEmployeesService: MaEmployeesService,
              private maMissionsService: MaMissionsService,
              private maMissionsEmployeesService: MaEmployeesMissionsService,
              private toast: ToastrService,
  ) {
  }

  ngOnInit(): void {
    this.getEmployeesNotDepartment();
    this.getAllMissions();
  }

  changeEmployeesSelected(listSelected: any) {
    this.listEmployeesAdd = this.listEmployees
      .filter(db => listSelected.includes(db.id))
      .map((db, index) =>
        ({...db, stt: index + 1, missionsListId: []}));
  }

  getEmployeesNotDepartment() {
    this.maEmployeesService.getAllEmployeesNotDepartments().subscribe({
      next: (response) => {
        this.listEmployees = response.data;
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

  handleCreate() {
    if (this.listEmployeesAdd.length == 0) {
      this.toast.warning("Chọn ít nhất 1 nhân viên để thêm vào phòng ban", "Thông báo",
        {
          positionClass: "toast-top-center"
        })
      return;
    }

    const dataCreate = this.listEmployeesAdd
      .map(i => ({
        departmentId: this.idDepartment,
        employeesId: i.id,
        missionsListId: i.missionsListId
      }))
    this.maMissionsEmployeesService.createMissionsEmployeesList(dataCreate).subscribe({
      next: (response) => {
        if (response.data) {
          const result: {
            id: string,
            code: string,
            birthday: Date,
            email: string,
            fullAddress: string,
            fullMissions: string,
            fullName: string,
            gender: string
            status: string
          }[] = response.data;
          this.toast.success('Thêm thành công', 'Thông báo');
          this.dialog.close(result);
        }
      }, error: (err) => {
        this.toast.error(err.error.message, 'Thông báo');
      }
    })
  }

  onCloseDialog() {
    this.dialog.close();
  }
}
