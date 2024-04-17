import {Component, OnInit} from '@angular/core';
import {AdDepartmentService} from "../../../service/ad-department.service";
import {AdEmployeesService} from "../../../service/ad-employees.service";
import {ToastrService} from "ngx-toastr";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-ma-department-create',
  templateUrl: './create.component.html',
  styleUrl: './create.component.css'
})
export class CreateComponent implements OnInit {

  objCreate: {
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
  } = {
    firstName: "",
    lastName: "",
    email: "",
    birthday: "",
    gender: "MALE",
    address: "",
    street: "",
    city: "",
    country: "Việt Nam",
    role: "STAFF",
    idDepartments: "",
  }
  listDepartments: any;
  provinceId: string = '';
  listCity: { ProvinceID: string, ProvinceName: string }[] = [];
  listStreet: string[] = [];

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

    this.adEmployeesSerivice.getAllCity().subscribe({
      next: (response) => {
        if (response.message == "Success") {
          this.listCity = response.data;
        }
      }
    })
  }

  onChangCity() {
    this.listStreet = [];
    this.objCreate.street = '';
    const objCity = this.listCity
      .find(city => city.ProvinceID.toString() === this.provinceId);
    if (objCity != undefined) {
      this.objCreate.city = objCity.ProvinceName;
      this.adEmployeesSerivice.getStreetByIdCountry(objCity.ProvinceID).subscribe({
        next: (response: any) => {
          if (response.message === "Success") {
            this.listStreet = response.data.map((item: any) => item.DistrictName);
          }
        }
      });
    }
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  createEmployees() {
    console.log(this.objCreate.birthday)
    this.adEmployeesSerivice.createEmployees(this.objCreate).subscribe({
      next: (response) => {
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
