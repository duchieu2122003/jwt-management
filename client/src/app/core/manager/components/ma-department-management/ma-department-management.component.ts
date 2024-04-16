import {Component, OnInit} from '@angular/core';
import {MaDepartmentsService} from "../../service/ma-departments.service";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {MaDepartmentCreateComponent} from "./ma-department-create/ma-department-create.component";
import {MaDepartmentUpdateComponent} from "./ma-department-update/ma-department-update.component";

@Component({
  selector: 'app-ma-department-management',
  templateUrl: './ma-department-management.component.html',
  styleUrl: './ma-department-management.component.css'
})
export class MaDepartmentManagementComponent implements OnInit {

  listDepartments: {
    stt: number,
    id: string,
    name: string, descriptions: string, status: string
  }[] = [];

  constructor(private maDepartmentsService: MaDepartmentsService,
              private toast: ToastrService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.maDepartmentsService.getAllDepartments().subscribe({
      next: (response) => {
        this.listDepartments = response.data
      }
    })
  }

  handleDelete(id: string) {
    this.maDepartmentsService.deleteDepartments(id).subscribe({
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
    const dialogRef = this.dialog.open(MaDepartmentCreateComponent, {
      width: '60%',
      // data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined) {
        this.listDepartments.push({...result.data, stt: this.listDepartments.length + 1});
      }
    });
  }

  openShowUpdate(department: any) {
    const dialogRef = this.dialog.open(MaDepartmentUpdateComponent, {
      width: '60%',
      data: {obj: department}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        const employeeToUpdate = this.listDepartments
          .find(item => item.id === result.data.id);
        if (employeeToUpdate) {
          Object.assign(employeeToUpdate, result.data, {stt: employeeToUpdate.stt});
        }
      }
    });
  }

}
