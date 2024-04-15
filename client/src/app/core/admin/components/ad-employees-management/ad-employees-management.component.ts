import {Component, OnInit} from '@angular/core';
import {AdEmployeesService} from "../../service/ad-employees.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {CreateComponent} from "./create/create.component";

@Component({
  selector: 'app-ad-employees-management',
  templateUrl: './ad-employees-management.component.html',
  styleUrl: './ad-employees-management.component.css'
})
export class AdEmployeesManagementComponent implements OnInit {
  title = "Quản lý tài khoản"

  filter: {
    code: string,
    name: string,
    email: string,
    city: string,
    status: string,
    page: number,
    size: number
  } = {
    code: "",
    name: "",
    email: "",
    city: "",
    status: "",
    page: 0,
    size: 10
  }

  listEmployees: any = [];

  constructor(public adEmployeesService: AdEmployeesService, private toast: ToastrService,
              private router: Router, private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.fetchData()
  }

  fetchData() {
    console.log(this.filter);
    this.adEmployeesService.getPageEmployees(this.filter).subscribe({
      next: (response) => {
        if (response.data.content.length > 0) {
          this.listEmployees = response.data.content;
        }
      }, error: (err) => {
        this.toast.error(err.message);
      }
    })
  }

  handleSearch() {
    this.fetchData();
  }

  handleClear() {
    this.filter = {
      code: "",
      name: "",
      email: "",
      city: "",
      status: "",
      page: 0,
      size: 10
    }
    this.fetchData();
  }

  openShowCreate() {
    const dialogRef = this.dialog.open(CreateComponent, {
      width: '80%',
      // data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined) {
        this.listEmployees.push({...result.data, stt: this.listEmployees.length + 1});
      }
    });
  }


}
