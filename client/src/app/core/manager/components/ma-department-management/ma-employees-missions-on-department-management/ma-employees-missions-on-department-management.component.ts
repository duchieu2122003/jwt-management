import {Component, OnInit} from '@angular/core';
import {MaDepartmentsService} from "../../../service/ma-departments.service";
import {ActivatedRoute} from "@angular/router";
import {MaEmployeesMissionsService} from "../../../service/ma-employees-missions.service";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {
  MaModalCreateEmployeeMissionComponent
} from "./ma-modal-create-employee-mission/ma-modal-create-employee-mission.component";
import {
  MaModalUpdateEmployeeMissionComponent
} from "./ma-modal-update-employee-mission/ma-modal-update-employee-mission.component";

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

  idDepartment = '';

  constructor(private maDepartmentsService: MaDepartmentsService,
              private maEmployeesMissionsService: MaEmployeesMissionsService,
              private toast: ToastrService,
              private routeParam: ActivatedRoute,
              private dialogRef: MatDialog,
  ) {
    this.routeParam.params.subscribe(params => {
      this.idDepartment = params['id'];
    })
  }

  ngOnInit(): void {
    this.getOneDepartmentsById(this.idDepartment);
    this.getAllEmployeesMissionOnDepartment(this.idDepartment);
  }

  getOneDepartmentsById(id: string) {
    this.maDepartmentsService.getOneDepartmentById(id).subscribe({
      next: (response) => {
        this.departments = response.data;
      }, error: (err) => {
        this.toast.error(err.error.message, "Thông báo");
      }
    })
  }

  getAllEmployeesMissionOnDepartment(id: string) {
    this.maEmployeesMissionsService.getAllEmployeesMissionOnDepartment(id).subscribe({
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
        width: '70%', minHeight: '70%', height: 'auto'
        , data: this.idDepartment
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

  openShowUpdate(id: string) {
    const dialogRef = this.dialogRef
      .open(MaModalUpdateEmployeeMissionComponent, {
        width: '80%'
      })
    dialogRef.afterClosed().subscribe(result => {

    })
  }

}
