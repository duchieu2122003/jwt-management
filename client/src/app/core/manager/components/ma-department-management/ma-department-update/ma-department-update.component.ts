import {Component, Inject, OnInit} from '@angular/core';
import {MaDepartmentsService} from "../../../service/ma-departments.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-ma-department-update',
  templateUrl: './ma-department-update.component.html',
  styleUrl: './ma-department-update.component.css'
})
export class MaDepartmentUpdateComponent implements OnInit {

  objUp: {
    id: string,
    name: string,
    descriptions: string
  } = {
    id: '',
    name: '',
    descriptions: ''
  }

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any, private maDepartmentService: MaDepartmentsService,
    private dialogRef: MatDialogRef<MaDepartmentUpdateComponent>,
    private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.objUp = this.data.obj;
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  updateDepartment() {
    this.maDepartmentService.updateDepartments(this.objUp).subscribe({
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
