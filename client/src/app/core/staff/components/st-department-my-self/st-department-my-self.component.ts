import {Component, OnInit} from '@angular/core';
import {StDepartmentService} from "../../service/st-department.service";
import {StEmployeesService} from "../../service/st-employees.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-st-department-my-self',
  templateUrl: './st-department-my-self.component.html',
  styleUrl: './st-department-my-self.component.css'
})
export class StDepartmentMySelfComponent implements OnInit {

  departments: {
    id: string,
    name: string,
    descriptions: string,
    status: string
  } = {
    id: '',
    name: '',
    descriptions: '',
    status: '',
  }
  listEmployees: {
    stt: number;
    id: string;
    code: string;
    fullName: string;
    email: string;
    birthday: Date;
    gender: string;
    fullAddress: string;
    status: string;
    fullMissions:string
  }[] = [];

  constructor(private stDepartmentService: StDepartmentService,
              private stEmployeesService: StEmployeesService,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.stDepartmentService.getDepartmentUserCurrent().subscribe({
      next: (response) => {
        console.log(response)
        if (response.data != null) {
          this.departments = response.data;
        }
      }, error: (err) => {
        this.toast.error(err.message, 'Thông báo');
      }
    })
    this.stEmployeesService.getListEmployeesOnDepartmentsUserCurrent().subscribe({
      next: (response) => {
        console.log(response)
        if (response.data != null) {
          this.listEmployees = response.data;
        }
      }, error: (err) => {
        this.toast.error(err.message, 'Thông báo');
      }
    })
  }
}
