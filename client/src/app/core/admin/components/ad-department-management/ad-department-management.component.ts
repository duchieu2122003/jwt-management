import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {AdDepartmentService} from "../../service/ad-department.service";
import {AdDepartmentCreateComponent} from "./ad-department-create/ad-department-create.component";
import {AdDepartmentUpdateComponent} from "./ad-department-update/ad-department-update.component";

@Component({
  selector: 'app-ad-department-management',
  templateUrl: './ad-department-management.component.html',
  styleUrl: './ad-department-management.component.css'
})
export class AdDepartmentManagementComponent implements OnInit {

   listDepartments: {
    stt: number,
    id: string,
    name: string,
    descriptions: string,
     status: string
  }[] = [];

  constructor(private adDepartmentsService: AdDepartmentService,
              private toast: ToastrService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.adDepartmentsService.getAllDepartmentView().subscribe({
      next: (response) => {
        this.listDepartments = response.data
      }
    })
  }

  handleDelete(id: string) {
    this.adDepartmentsService.deleteDepartments(id).subscribe({
      next: (response) => {
        if (response.data == true) {
          this.listDepartments = this.listDepartments.filter(i => i.id !== id);
          this.listDepartments.forEach((i, index) => {
            i.stt = index + 1;
          });
          this.toast.success("Xóa thành công", "Thông báo")
        } else {
          this.toast.error("Xóa thất bại", "Thông báo")
        }
      }, error: (err) => {
        this.toast.error(err.error.message, "Thông báo")
      }
    })
  }

  openShowCreate() {
    const dialogRef = this.dialog.open(AdDepartmentCreateComponent, {
      width: '60%',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.listDepartments.push({...result.data, stt: this.listDepartments.length + 1});
      }
    });
  }

  openShowUpdate(department: any) {
    const dialogRef = this.dialog.open(AdDepartmentUpdateComponent, {
      width: '60%',
      data: {obj: department}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const employeeToUpdate = this.listDepartments
          .find(item => item.id === result.data.id);
        if (employeeToUpdate) {
          Object.assign(employeeToUpdate, result.data, {stt: employeeToUpdate.stt});
        }
      }
    });
  }
}
