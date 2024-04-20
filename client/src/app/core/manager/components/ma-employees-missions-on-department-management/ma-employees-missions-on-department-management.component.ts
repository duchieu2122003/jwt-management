import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {
  MaModalCreateEmployeeMissionComponent
} from "./ma-modal-create-employee-mission/ma-modal-create-employee-mission.component";
import {
  MaModalUpdateEmployeeMissionComponent
} from "./ma-modal-update-employee-mission/ma-modal-update-employee-mission.component";
import {MaDepartmentsService} from "../../service/ma-departments.service";
import {MaEmployeesMissionsService} from "../../service/ma-employees-missions.service";
import {MaEmployeesService} from "../../service/ma-employees.service";
import {jwtDecode} from "jwt-decode";


interface UserDecode {
  id: string;
}

@Component({
  selector: 'app-ma-employees-missions-on-department-management',
  templateUrl: './ma-employees-missions-on-department-management.component.html',
  styleUrl: './ma-employees-missions-on-department-management.component.css'
})
export class MaEmployeesMissionsOnDepartmentManagementComponent implements OnInit {

  departments = {
    id: '',
    name: '',
    descriptions: '',
    status: '',
  }

  listEmployeesMissions: {
    stt: number,
    id: string,
    birthday: string,
    code: string
    email: string,
    fullAddress: string,
    fullMissions: string,
    fullName: string,
    gender: string,
    status: string;
  }[] = [];
  employeeCurrent: UserDecode = {
    id: '',
  };

  constructor(private maDepartmentsService: MaDepartmentsService,
              private maEmployeesMissionsService: MaEmployeesMissionsService,
              private maEmployeesService: MaEmployeesService,
              private toast: ToastrService,
              private dialogRef: MatDialog,
  ) {
  }

  ngOnInit(): void {
    this.getIdEmployeesGenToken();
    this.getDepartmentsUserCurrent();
  }

  getIdEmployeesGenToken() {
    const token = localStorage.getItem('token');
    if (token) {
      this.employeeCurrent = jwtDecode(token);
    }
  }

  getDepartmentsUserCurrent() {
    this.maDepartmentsService.getDepartmentsUserCurrent().subscribe({
      next: (response) => {
        this.departments = response.data;
        this.getAllEmployeesMissionOnDepartment(this.departments.id);
      }, error: (err) => {
        this.toast.error(err.error.message, 'Thông báo');
      }
    })
  }

  getAllEmployeesMissionOnDepartment(idDepartment: string) {
    this.maEmployeesMissionsService.getAllEmployeesMissionOnDepartment(idDepartment).subscribe({
      next: (response) => {
        this.listEmployeesMissions = response.data;
      }, error: (err) => {
        this.toast.error(err.error.message, "Thông báo");
      }
    })
  }

  openShowCreate() {
    const dialogRef = this.dialogRef
      .open(MaModalCreateEmployeeMissionComponent, {
        width: '70%', minHeight: '70%', height: 'auto',
        data: this.departments.id
      })

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const listAddOk = result.map((item: any) => {
          return {
            ...item,
            stt: this.listEmployeesMissions.length + 1
          };
        });
        this.listEmployeesMissions = this.listEmployeesMissions.concat(listAddOk);
      }
    })
  }

  openShowUpdate(idEmployees: string) {
    const dialogRef = this.dialogRef
      .open(MaModalUpdateEmployeeMissionComponent, {
        width: '60%',
        data: idEmployees,
      })
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const objUpdate = this.listEmployeesMissions
          .find(i => i.id === result.data.id)
        if (objUpdate) {
          Object.assign(objUpdate, result.data, {stt: objUpdate.stt})
        }
      }
    })
  }

  handleDeleteIdDepartmentEmployees(id: string) {
    this.maEmployeesService.deleteEmployeesOutDepartment(id).subscribe({
      next: (response) => {
        if (response.data == true) {
          this.toast.success("Xóa nhân viên khỏi phòng ban thành công", "Thông báo")
          this.listEmployeesMissions = this.listEmployeesMissions
            .filter(d => d.id !== id);
        } else {
          this.toast.error("Xóa nhân viên khỏi phòng ban thất bại", "Thông báo");
        }
      }, error: (err) => {
        this.toast.error(err.error.message, "Thông báo");
      }
    })
  }


}
