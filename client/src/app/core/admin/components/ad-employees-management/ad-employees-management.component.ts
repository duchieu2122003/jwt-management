import {Component, OnInit} from '@angular/core';
import {AdEmployeesService} from "../../service/ad-employees.service";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {CreateComponent} from "./create/create.component";
import {UpdateComponent} from "./update/update.component";

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

  totalsPage: number = 0;
  listTotalsPage: number[] = [];


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
  }[] = [];
  listCity: any = [];

  constructor(private adEmployeesService: AdEmployeesService,
              private toast: ToastrService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.fetchCity();
    this.fetchData();
  }

  fetchData() {
    this.adEmployeesService.getPageEmployees(this.filter).subscribe({
      next: (response) => {
        if (response.data != null) {
          this.listEmployees = response.data.content;
        }
        this.listTotalsPage = [];
        this.totalsPage = response.data.totalPages;
        for (let i = 1; i <= this.totalsPage; i++) {
          this.listTotalsPage.push(i);
        }
      }, error: (err) => {
        this.toast.error(err.message);
      }
    })
  }

  fetchCity() {
    this.adEmployeesService.getAllCity().subscribe({
      next: (response) => {
        if (response.message == "Success") {
          this.listCity = response.data.map((i: { ProvinceName: any; }) => {
            return i.ProvinceName;
          });
        }
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
        this.listEmployees.unshift({...result.data, stt: 1});
        this.listEmployees.map((i, index) => i.stt = index + 1);
      }
    });
  }

  openShowUpdate(id: string) {
    const dialogRef = this.dialog.open(UpdateComponent, {
      width: '80%',
      data: {id: id}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        const employeeToUpdate = this.listEmployees
          .find(item => item.id === result.data.id);
        if (employeeToUpdate) {
          Object.assign(employeeToUpdate, result.data, {stt: employeeToUpdate.stt});
        }
      }
    });

  }

  handleDelete(id: any) {
    this.adEmployeesService.deleteEmployees(id).subscribe({
      next: (response) => {
        if (response.data == true) {
          this.listEmployees = this.listEmployees.filter(employee => employee.id !== id);
          this.listEmployees.forEach((employee, index) => {
            employee.stt = index + 1;
          });
          this.toast.success("Xóa thành công")
        } else {
          this.toast.error("Xóa thất bại")
        }
      }
    })
  }

  handleNext() {
    if (this.filter.page < this.totalsPage - 1) {
      this.filter.page++;
      this.handleSearch();
    }
  }

  handlePrevious() {
    if (this.filter.page > 0) {
      this.filter.page--;
      this.handleSearch();
    }
  }

  handleSearchPage(currentPage: number) {
    this.filter.page = currentPage;
    this.fetchData();
  }
}
