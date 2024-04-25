import {Component, OnInit} from '@angular/core';
import moment from "moment/moment";
import {ToastrService} from "ngx-toastr";
import {CoEmployeesService} from "../../service/co-employees.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-information-my-self',
  templateUrl: './information-my-self.component.html',
  styleUrl: './information-my-self.component.css'
})
export class InformationMySelfComponent implements OnInit {

  edit: boolean = false;
  objUpdate: {
    id: string,
    code: string,
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
    departmentName: string,
    statusBoolean: boolean,
    status: string
  } = {
    id: "",
    code: "",
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
    departmentName: "",
    statusBoolean: true,
    status: "ACTIVE"
  };

  provinceId: string = '';
  listCity: { ProvinceID: string, ProvinceName: string }[] = [];
  listStreet: string[] = [];
  objInit: any;

  constructor(private coEmployeesService: CoEmployeesService,
              private toast: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.coEmployeesService.detailUserCurrent().subscribe({
      next: (response) => {
        this.objUpdate = response.data;
        this.objUpdate.statusBoolean = response.data.status === "ACTIVE";
        this.objUpdate.birthday = moment(response.data.birthday).format('YYYY-MM-DD');
        this.objInit = {...this.objUpdate};
      }, error: (err) => {
        this.toast.error(err.error.message, 'Thông báo')
      }
    })

    this.coEmployeesService.getAllCity().subscribe({
      next: (response) => {
        if (response.message == "Success") {
          this.listCity = response.data;
          this.getStreetByNameCity();
        }
      }
    })
  }

  getStreetByNameCity() {
    const objCity = this.listCity
      .find(city => city.ProvinceName === this.objUpdate.city);
    if (objCity != undefined) {
      this.provinceId = objCity.ProvinceID;
      this.coEmployeesService.getStreetByIdCountry(objCity.ProvinceID).subscribe({
          next: (response) => {
            if (response.message == "Success") {
              this.listStreet = response.data.map((i: { DistrictName: any; }) => i.DistrictName);
            }
          }
        }
      )
    }
  }

  onChangCity() {
    this.listStreet = [];
    this.objUpdate.street = '';
    const objCity = this.listCity
      .find(city => city.ProvinceID.toString() === this.provinceId);
    if (objCity != undefined) {
      this.objUpdate.city = objCity.ProvinceName;
      this.coEmployeesService.getStreetByIdCountry(objCity.ProvinceID).subscribe({
        next: (response: any) => {
          if (response.message === "Success") {
            this.listStreet = response.data.map((item: any) => item.DistrictName);
          }
        }
      });
    }
  }

  handleUpdateEmployeesCurrent() {
    this.coEmployeesService.updateUserCurrent(this.objUpdate).subscribe({
      next: (response) => {
        sessionStorage.setItem('token', response.data.token);
        this.toast.success("Sửa thông tin thành công", "Thông báo");
        this.edit = false;
      }, error: (err) => {
        this.toast.error(err.error.message, "Thông báo");
      }
    })
  }

  onChangeEdit() {
    this.edit = !this.edit;
  }

  onCloseEdit() {
    this.edit = false;
    this.objUpdate = {...this.objInit};
  }

  redirectTo(url: string) {
    this.router.navigate([`` + url])
  }
}
