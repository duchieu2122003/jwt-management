import {Component, Inject, OnInit} from '@angular/core';
import {AdEmployeesService} from "../../../service/ad-employees.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AdDepartmentService} from "../../../service/ad-department.service";
import {ToastrService} from "ngx-toastr";
import moment from "moment";

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrl: './update.component.css'
})
export class UpdateComponent implements OnInit {
  objUpdate: {
    id: string,
    firstName: string,
    lastName: string,
    email: string,
    birthday: string,
    gender: string,
    address: string,
    street: string,
    city: string,
    country: string,
    role: string,
    idDepartments: string,
    statusBoolean: boolean,
    status: string
  } = {
    id: "",
    firstName: "",
    lastName: "",
    email: "",
    birthday: "",
    gender: "MALE",
    address: "",
    street: "",
    city: "",
    country: "",
    role: "STAFF",
    idDepartments: "",
    statusBoolean: true,
    status: "ACTIVE"
  };
  listDepartments: any

  constructor(private adEmployeesService: AdEmployeesService,
              @Inject(MAT_DIALOG_DATA) private data: { id: string },
              private dialogRef: MatDialogRef<UpdateComponent>,
              private adDepartmentsService: AdDepartmentService,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.adDepartmentsService.getAllDepartment().subscribe({
      next: (response) => {
        this.listDepartments = response.data;
      }
    })
    this.adEmployeesService.getDetailEmployees(this.data.id).subscribe({
      next: (response) => {
        this.objUpdate = response.data;
        this.objUpdate.statusBoolean = response.data.status === "ACTIVE";
        this.objUpdate.birthday = moment(response.data.birthday).format('YYYY-MM-DD');
        console.log(this.objUpdate)
      }, error: (err) => {
        this.toast.error(err.error.message, 'Thông báo')
      }
    })

  }

  closeDialog() {
    this.dialogRef.close();
  }

  updateEmployees() {
    if (this.objUpdate.statusBoolean) {
      this.objUpdate.status = "ACTIVE";
    } else {
      this.objUpdate.status = "INACTIVE";
    }
    this.adEmployeesService.updateEmployees(this.objUpdate).subscribe({
      next: (response) => {
        this.toast.success("Sửa thành công", "Thông báo", {
          positionClass: 'toast-top-center',
          timeOut: 3000
        })
        this.dialogRef.close(response);
      }, error: (err) => {
        this.toast.error(err.error.message, "Thông báo", {
          positionClass: 'toast-top-center',
          timeOut: 3000
        })
      }
    })
  }
}
