import {Component, Inject, OnInit} from '@angular/core';
import {AdEmployeesService} from "../../../service/ad-employees.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AdDepartmentService} from "../../../service/ad-department.service";
import {ToastrService} from "ngx-toastr";
import moment from "moment";

@Component({
  selector: 'app-update',
  templateUrl: './ad-update-employees.component.html',
  styleUrl: './ad-update-employees.component.css'
})
export class AdUpdateEmployeesComponent implements OnInit {
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
  listCity: { ProvinceID: string, ProvinceName: string }[] = [];
  listStreet: string[] = [];
  provinceId: string = '';

  constructor(private adEmployeesService: AdEmployeesService,
              @Inject(MAT_DIALOG_DATA) private data: { id: string },
              private dialogRef: MatDialogRef<AdUpdateEmployeesComponent>,
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
      }, error: (err) => {
        this.toast.error(err.error.message, 'Thông báo')
      }
    })

    this.adEmployeesService.getAllCity().subscribe({
      next: (response) => {
        if (response.message == "Success") {
          this.listCity = response.data;
          this.getStreetByNameCity();
        }
      }
    })

  }

  closeDialog() {
    this.dialogRef.close();
  }

  onChangCity() {
    this.listStreet = [];
    this.objUpdate.street = '';
    const objCity = this.listCity
      .find(city => city.ProvinceID.toString() === this.provinceId);
    if (objCity != undefined) {
      this.objUpdate.city = objCity.ProvinceName;
      this.getStreetByProvinceId(objCity.ProvinceID);
    }
  }

  getStreetByNameCity() {
    const objCity = this.listCity
      .find(city => city.ProvinceName === this.objUpdate.city);
    if (objCity != undefined) {
      this.provinceId = objCity.ProvinceID;
      this.adEmployeesService.getStreetByIdCountry(objCity.ProvinceID).subscribe({
          next: (response) => {
            if (response.message == "Success") {
              this.listStreet = response.data.map((i: { DistrictName: any; }) => i.DistrictName);
            }
          }
        }
      )
    }
  }

  getStreetByProvinceId(provinceId: string) {
    this.adEmployeesService.getStreetByIdCountry(provinceId).subscribe({
      next: (response: any) => {
        if (response.message === "Success") {
          this.listStreet = response.data.map((item: any) => item.DistrictName);
        }
      }
    });
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
