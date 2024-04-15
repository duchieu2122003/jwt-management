import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AdDepartmentService} from "../../../service/ad-department.service";
import {AdEmployeesService} from "../../../service/ad-employees.service";
import {ToastrService} from "ngx-toastr";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrl: './create.component.css'
})
export class CreateComponent implements OnInit {

  objCreate: {
    firstName: string,
    lastName: string,
    email: string,
    birthday: Date,
    gender: string,
    address: string,
    street: string,
    city: string,
    country: string,
    role: string,
    idDepartments: string,
  } = {
    firstName: "",
    lastName: "",
    email: "",
    birthday: new Date(),
    gender: "MALE",
    address: "",
    street: "",
    city: "",
    country: "",
    role: "STAFF",
    idDepartments: "",
  }
  listDepartments: any;

  @Output() openModal = new EventEmitter();

  constructor(private dialogRef: MatDialogRef<CreateComponent>,
              private adDeparmentService: AdDepartmentService,
              public adEmployeesSerivice: AdEmployeesService,
              private toast: ToastrService) {
  }


  ngOnInit(): void {
    this.adDeparmentService.getAllDepartment().subscribe({
      next: (response) => {
        this.listDepartments = response.data;
      }
    })
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  createEmployees() {
    console.log(this.objCreate)
    this.adEmployeesSerivice.createEmployees(this.objCreate).subscribe({
      next: (response) => {
        console.log(response)
        this.toast.success("Thêm thành công", "Thông báo", {
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
